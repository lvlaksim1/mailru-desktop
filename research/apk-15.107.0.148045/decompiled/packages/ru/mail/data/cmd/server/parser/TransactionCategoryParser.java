package ru.mail.data.cmd.server.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.MailItemTransactionCategory;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class TransactionCategoryParser extends JSONParser<MailItemTransactionCategory> {
    private static final String TRANSACTION_CATEGORY_KEY = "transaction_category";
    private final Map<String, MailItemTransactionCategory> mTransactionCategoriesMap = new HashMap();

    public TransactionCategoryParser() {
        initTransactionCategoriesMap();
    }

    private void initTransactionCategoriesMap() {
        for (MailItemTransactionCategory mailItemTransactionCategory : MailItemTransactionCategory.values()) {
            this.mTransactionCategoriesMap.put(mailItemTransactionCategory.toString(), mailItemTransactionCategory);
        }
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    public MailItemTransactionCategory parse(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("transaction_category")) {
            return MailItemTransactionCategory.NO_CATEGORIES;
        }
        MailItemTransactionCategory mailItemTransactionCategory = this.mTransactionCategoriesMap.get(jSONObject.getString("transaction_category"));
        return mailItemTransactionCategory != null ? mailItemTransactionCategory : MailItemTransactionCategory.NO_CATEGORIES;
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    public List<MailItemTransactionCategory> parse(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < jSONArray.length(); i10++) {
            MailItemTransactionCategory mailItemTransactionCategory = this.mTransactionCategoriesMap.get(jSONArray.getString(i10).toLowerCase(Locale.US));
            if (mailItemTransactionCategory != null) {
                arrayList.add(mailItemTransactionCategory);
            }
        }
        return arrayList;
    }
}
