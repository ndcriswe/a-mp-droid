package com.mediaportal.ampdroid.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.api.DataHandler;
import com.mediaportal.ampdroid.data.TvProgramBase;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter;
import com.mediaportal.ampdroid.utils.Util;

  public class CancelScheduleTask extends AsyncTask<TvProgramBase, Boolean, Boolean> {
      private Context mContext;
      private DataHandler mService;
      private LazyLoadingAdapter mEpgAdapter;

      public CancelScheduleTask(Context _context, DataHandler _service, LazyLoadingAdapter _adapter) {
         mContext = _context;
         mService = _service;
         mEpgAdapter = _adapter;
      }

      @Override
      protected Boolean doInBackground(TvProgramBase... _params) {
         TvProgramBase program = _params[0];
         mService.cancelTvScheduleByProgramId(program.getIdProgram());

         program.setIsScheduled(false);

         return true;
      }

      @Override
      protected void onPostExecute(Boolean _result) {
         if (_result) {
            Util.showToast(mContext, mContext.getString(R.string.tvserver_schedulecanceled));

            mEpgAdapter.notifyDataSetChanged();
         } else {
            Util.showToast(mContext,  mContext.getString(R.string.tvserver_schedulecancel_failed));
         }
      }
   }