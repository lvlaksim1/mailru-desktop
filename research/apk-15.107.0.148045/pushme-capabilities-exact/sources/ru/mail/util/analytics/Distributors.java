package ru.mail.util.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import ru.mail.locator.Locator;
import ru.mail.logic.content.DistributorStore;
import ru.mail.mails.R;
import ru.mail.sdk.BuildConfigVariablesHolder;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class Distributors {
    public static final String KEY_PREF_FIRST_APP_DISTRIBUTOR = "first_app_distributor";
    public static final String CURRENT_DISTRIBUTOR = BuildConfigVariablesHolder.distributor;
    private static final AtomicReference<String> sPubNativeId = new AtomicReference<>();
    private static final AtomicReference<String> sPlacementId = new AtomicReference<>();

    /* JADX INFO: compiled from: ProGuard */
    public static class Distributor {
        private String mName;

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes13.dex */
        private enum Placement {
            PRESTIGIO(R.string.prestigio_fb_placement_id);


            @StringRes
            private int mId;

            Placement(int i10) {
                this.mId = i10;
            }

            @StringRes
            static int forDistributor(@NonNull String str) {
                String upperCase = str.toUpperCase(Locale.ENGLISH);
                int i10 = R.string.facebook_placement_id;
                for (Placement placement : values()) {
                    if (placement.name().equals(upperCase)) {
                        return placement.mId;
                    }
                }
                return i10;
            }
        }

        private Distributor(String str) {
            this.mName = str;
        }

        public static Distributor from(@NonNull String str) {
            return new Distributor(str);
        }

        public String getName() {
            return this.mName;
        }

        public String getPlacementId(Context context) {
            return context.getString(Placement.forDistributor(this.mName));
        }

        public String toString() {
            return this.mName;
        }
    }

    public static Distributor getFirstDistributor(Context context) {
        return Distributor.from(((DistributorStore) Locator.from(context).locate(DistributorStore.class)).getDistributor());
    }

    public static String getLocalFbPlacementId() {
        return sPlacementId.get();
    }

    public static String getLocalPubNativeId() {
        return sPubNativeId.get();
    }

    public static void setFbPlacementId(String str) {
        sPlacementId.getAndSet(str);
    }

    public static void setFirstIfNeeded(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (TextUtils.isEmpty(defaultSharedPreferences.getString(KEY_PREF_FIRST_APP_DISTRIBUTOR, null))) {
            defaultSharedPreferences.edit().putString(KEY_PREF_FIRST_APP_DISTRIBUTOR, CURRENT_DISTRIBUTOR).apply();
        }
    }

    public static void setPubNativeId(String str) {
        sPubNativeId.getAndSet(str);
    }
}
