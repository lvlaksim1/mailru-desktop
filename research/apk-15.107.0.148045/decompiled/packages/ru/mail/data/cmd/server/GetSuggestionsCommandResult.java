package ru.mail.data.cmd.server;

import java.util.Collections;
import java.util.List;
import ru.mail.logic.search.SearchSuggestion;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class GetSuggestionsCommandResult {
    private final List<SearchSuggestion> mSuggestions;

    public GetSuggestionsCommandResult(List<SearchSuggestion> list) {
        this.mSuggestions = Collections.unmodifiableList(list);
    }

    public List<SearchSuggestion> getSearchSuggestions() {
        return this.mSuggestions;
    }
}
