package ru.mail.data.cmd.server;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.roomorama.caldroid.CaldroidFragment;
import com.vk.stat.refs.RefsKt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.entities.Collector;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.domain.Contact;
import ru.mail.domain.Phone;
import ru.mail.domain.Social;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;
import ru.mail.utils.JsonUtils;
import ru.mail.utils.StringEscapeUtils;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "ab", "smart"})
public class AddressBookFetchV2 extends ServerCommandBase<Params, Result> {
    private static final Log LOG = Log.getLog("AddressBookFetchV2");

    @Param(getterName = "getAcceptEncoding", method = HttpMethod.HEADER_ADD, name = "Accept-Encoding", useGetter = true)
    private String mAcceptEncoding;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Keep
        @Param(method = HttpMethod.GET, name = "limit")
        static final int LIMIT = Integer.MAX_VALUE;

        public Params(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final List<Contact> mAddressBook;

        public Result(List<Contact> list) {
            this.mAddressBook = list;
        }

        public List<Contact> getAddressBook() {
            return this.mAddressBook;
        }
    }

    public AddressBookFetchV2(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    private static String checkNullStr(String str) {
        if ("null".equals(str)) {
            return null;
        }
        return str;
    }

    private String findAppropriateLabelName(@NonNull JSONArray jSONArray, String str) {
        String strOptStringWithReplacedCodes;
        for (JSONObject jSONObject : JsonUtils.wrapWithIterable(jSONArray)) {
            if (jSONObject != null && (strOptStringWithReplacedCodes = optStringWithReplacedCodes(jSONObject, "id")) != null && strOptStringWithReplacedCodes.equals(str)) {
                return optStringWithReplacedCodes(jSONObject, "name");
            }
        }
        return null;
    }

    @androidx.annotation.Nullable
    private List<String> getLabels(@NonNull JSONObject jSONObject, @NonNull JSONArray jSONArray) throws JSONException {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("labels");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArrayOptJSONArray.length());
        for (JSONObject jSONObject2 : JsonUtils.wrapWithIterable(jSONArrayOptJSONArray)) {
            if (jSONObject2 == null) {
                throw new JSONException("Label wasn't parsed correctly");
            }
            String strFindAppropriateLabelName = findAppropriateLabelName(jSONArray, jSONObject2.toString());
            if (strFindAppropriateLabelName != null) {
                arrayList.add(strFindAppropriateLabelName);
            }
        }
        return arrayList;
    }

    private int getPhoneType(String str) {
        if ("mobile".equals(str)) {
            return 2;
        }
        if (RefsKt.REFER_HOME.equals(str)) {
            return 1;
        }
        if (Collector.STATE_WORK.equals(str)) {
            return 3;
        }
        if ("fax".equals(str)) {
            return 13;
        }
        "other".equals(str);
        return 7;
    }

    private List<Phone> getPhones(JSONObject jSONObject, Contact contact) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("phones");
        if (jSONArrayOptJSONArray != null) {
            for (JSONObject jSONObject2 : JsonUtils.wrapWithIterable(jSONArrayOptJSONArray)) {
                if (jSONObject2 == null) {
                    throw new JSONException("Phone wasn't parsed correctly");
                }
                String strOptStringWithReplacedCodes = optStringWithReplacedCodes(jSONObject2, "type");
                String strOptStringWithReplacedCodes2 = optStringWithReplacedCodes(jSONObject2, "phone");
                int phoneType = getPhoneType(strOptStringWithReplacedCodes);
                if (strOptStringWithReplacedCodes2 == null) {
                    strOptStringWithReplacedCodes2 = "";
                }
                arrayList.add(new Phone(contact, phoneType, strOptStringWithReplacedCodes2));
            }
        }
        return arrayList;
    }

    @NonNull
    private List<Social> getSocials(@NonNull JSONObject jSONObject, Contact contact) throws JSONException {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("social");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(jSONArrayOptJSONArray.length());
        for (JSONObject jSONObject2 : JsonUtils.wrapWithIterable(jSONArrayOptJSONArray)) {
            if (jSONObject2 == null) {
                throw new JSONException("Social wasn't parsed correctly");
            }
            String strOptStringWithReplacedCodes = optStringWithReplacedCodes(jSONObject2, "type");
            if (!TextUtils.isEmpty(strOptStringWithReplacedCodes)) {
                String strOptStringWithReplacedCodes2 = optStringWithReplacedCodes(jSONObject2, "account");
                if (!TextUtils.isEmpty(strOptStringWithReplacedCodes2)) {
                    arrayList.add(new Social(strOptStringWithReplacedCodes, strOptStringWithReplacedCodes2, optStringWithReplacedCodes(jSONObject2, "displayname"), contact));
                }
            }
        }
        return arrayList;
    }

    @androidx.annotation.Nullable
    private String optStringWithReplacedCodes(@NonNull JSONObject jSONObject, @NonNull String str) {
        String strOptString = jSONObject.optString(str);
        return checkNullStr(!TextUtils.isEmpty(strOptString) ? StringEscapeUtils.replaceSpecialCodes(strOptString) : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Contact parseContact(@NonNull JSONObject jSONObject, @NonNull JSONArray jSONArray) throws JSONException {
        String strOptStringWithReplacedCodes;
        String strOptStringWithReplacedCodes2;
        int iOptInt;
        int iOptInt2;
        int i10 = jSONObject.getInt("priority");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("name");
        if (jSONObjectOptJSONObject != null) {
            strOptStringWithReplacedCodes2 = optStringWithReplacedCodes(jSONObjectOptJSONObject, PreferenceHostProvider.URL_PARAM_FIRST);
            strOptStringWithReplacedCodes = optStringWithReplacedCodes(jSONObjectOptJSONObject, MailThreadRepresentation.COL_NAME_LAST);
        } else {
            strOptStringWithReplacedCodes = null;
            strOptStringWithReplacedCodes2 = null;
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("birthday");
        int i11 = -1;
        if (jSONObjectOptJSONObject2 != null) {
            int iOptInt3 = jSONObjectOptJSONObject2.optInt("day", -1);
            iOptInt2 = jSONObjectOptJSONObject2.optInt(CaldroidFragment.MONTH, -1);
            iOptInt = jSONObjectOptJSONObject2.optInt(CaldroidFragment.YEAR, -1);
            i11 = iOptInt3;
        } else {
            iOptInt = -1;
            iOptInt2 = -1;
        }
        String strOptStringWithReplacedCodes3 = optStringWithReplacedCodes(jSONObject, "nick");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("emails");
        String string = (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) ? null : jSONArrayOptJSONArray.getString(0);
        if (string == null) {
            return null;
        }
        String strOptStringWithReplacedCodes4 = optStringWithReplacedCodes(jSONObject, "sex");
        String strOptStringWithReplacedCodes5 = optStringWithReplacedCodes(jSONObject, "company");
        String strOptStringWithReplacedCodes6 = optStringWithReplacedCodes(jSONObject, "job_title");
        String strOptStringWithReplacedCodes7 = optStringWithReplacedCodes(jSONObject, "boss");
        int i12 = iOptInt;
        Contact contactBuild = new Contact.Builder(string).setName(strOptStringWithReplacedCodes2).setLastName(strOptStringWithReplacedCodes).setNick(strOptStringWithReplacedCodes3).setPriority(i10).setAccount(((Params) getParams()).getLogin()).setGender(strOptStringWithReplacedCodes4).setCompany(strOptStringWithReplacedCodes5).setJobTitle(strOptStringWithReplacedCodes6).setBoss(strOptStringWithReplacedCodes7).setAddress(optStringWithReplacedCodes(jSONObject, "address")).setComment(optStringWithReplacedCodes(jSONObject, "comment")).setBirthday(i11, iOptInt2, i12).setLabels(getLabels(jSONObject, jSONArray)).setServerId(optStringWithReplacedCodes(jSONObject, "id")).build();
        contactBuild.setSocials(getSocials(jSONObject, contactBuild));
        contactBuild.setPhones(getPhones(jSONObject, contactBuild));
        return contactBuild;
    }

    @Keep
    public String getAcceptEncoding() {
        return "gzip";
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    AddressBookFetchV2(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            JSONArray jSONArray = jSONObject.getJSONArray("contacts");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("labels");
            if (jSONArrayOptJSONArray == null) {
                jSONArrayOptJSONArray = new JSONArray();
            }
            for (JSONObject jSONObject2 : JsonUtils.wrapWithIterable(jSONArray)) {
                if (jSONObject2 == null) {
                    throw new JSONException("Label wasn't parsed correctly");
                }
                Contact contact = parseContact(jSONObject2, jSONArrayOptJSONArray);
                if (contact != null) {
                    arrayList.add(contact);
                }
            }
            return new Result(arrayList);
        } catch (JSONException e10) {
            e10.printStackTrace();
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
