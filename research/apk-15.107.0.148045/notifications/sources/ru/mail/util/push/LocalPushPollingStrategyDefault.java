package ru.mail.util.push;

import android.content.Context;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.commons.collections4.CollectionUtils;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.pushfilters.LoadFiltersDbCommand;
import ru.mail.locator.Locator;
import ru.mail.logic.pushfilters.FilterAccessor;
import ru.mail.logic.pushfilters.PushFilter;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class LocalPushPollingStrategyDefault implements LocalPushPollingStrategy {
    private static final Log LOG = Log.getLog("LocalPushPollingStrategyDefault");

    @Override // ru.mail.util.push.LocalPushPollingStrategy
    public Collection<Long> getCheckedFolders(Context context, String str) {
        ObservableFuture<AsyncDbHandler.CommonResponse<T, ID>> observableFutureExecute = new LoadFiltersDbCommand(context).execute((ExecutorSelector) Locator.locate(context, RequestArbiter.class));
        List listSingletonList = Collections.singletonList(0L);
        try {
            FilterAccessor filterAccessor = (FilterAccessor) ((AsyncDbHandler.CommonResponse) observableFutureExecute.getOrThrow()).getObj();
            if (filterAccessor != null) {
                PushFilter.Type type = PushFilter.Type.FOLDER;
                Iterable iterableSelect = filterAccessor.get(type);
                if (filterAccessor.getGroupFilter(type).getState()) {
                    iterableSelect = CollectionUtils.select(iterableSelect, new FilterAccessor.PushFilterByParams(str, true));
                }
                return CollectionUtils.collect(iterableSelect, FilterAccessor.CONVERTER_FILTERS_TO_ITEM_ID);
            }
        } catch (InterruptedException e10) {
            e = e10;
            LOG.e("error while execution commands" + e);
        } catch (ExecutionException e11) {
            e = e11;
            LOG.e("error while execution commands" + e);
        }
        return listSingletonList;
    }
}
