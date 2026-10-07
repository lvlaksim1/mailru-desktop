package ru.mail.registration.validator;

import android.text.TextUtils;
import java.util.List;
import ru.mail.Authenticator.R;
import ru.mail.util.log.Log;
import ru.mail.utils.ResourceProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class CaptchaValidator extends BaseStringValidator {
    private static final Log LOG = Log.getLog("CaptchaValidator");
    private static final String PATTERN = "[^a-zA-Z0-9]+";

    public CaptchaValidator(ResourceProvider resourceProvider) {
        super(resourceProvider);
    }

    @Override // ru.mail.registration.validator.UserDataValidator
    public UserDataValidator.Result getValidationResult(String str) {
        if (TextUtils.isEmpty(str)) {
            return new UserDataValidator.ResStrResult(R.string.reg_err_captcha_small);
        }
        List<String> invalidChars = getInvalidChars(PATTERN, str);
        return !invalidChars.isEmpty() ? new BaseStringValidator.InvalidCharsResult(R.string.reg_err_captcha_should_not_contain, invalidChars) : new UserDataValidator.OkResult();
    }
}
