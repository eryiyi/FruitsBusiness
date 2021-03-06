package com.lbins.FruitsBusiness;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.lbins.FruitsBusiness.bean.BaseActivity;
import com.lbins.FruitsBusiness.bean.Response;
import com.lbins.FruitsBusiness.fragment.BottomFragment;
import com.lbins.FruitsBusiness.fragment.MainPageFragment;
import com.lbins.FruitsBusiness.fragment.PersonalcenterFragment;
import com.lbins.FruitsBusiness.fragment.PublicTitleFragment;
import com.lbins.FruitsBusiness.http.AsyncHttpResponseHandler;
import com.lbins.FruitsBusiness.http.HttpClientUtils;
import com.lbins.FruitsBusiness.http.HttpParams;
import com.lbins.FruitsBusiness.servieid.CheckNetwork;
import com.lbins.FruitsBusiness.servieid.ServerId;
import com.lbins.FruitsBusiness.util.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity implements BottomFragment.OnBottomClickListener {
    BottomFragment bottomFragment;
    private long mExitTime;
    FragmentManager fManager;
    private MainPageFragment fg1;
    private PersonalcenterFragment fg4;
    private FragmentTransaction transaction;
    private static int getskip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        save("is_login", "0");
        save("uid", "");
        save("password", "");
        save("user_name", "");
        Fragment mainpagetilte = new PublicTitleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("changetitle", BottomFragment.MAINPAGE);
        mainpagetilte.setArguments(bundle);
        // getSupportFragmentManager().beginTransaction().add(R.id.secondappcontent,
        // new MainPageFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.titlefragment, mainpagetilte).commit();
        fManager = getSupportFragmentManager();
        getskip = getIntent().getIntExtra("personcenter", 0);
        setChioceItem(getskip);
        bottomFragment = (BottomFragment) getSupportFragmentManager().findFragmentById(R.id.bottombar);
        bottomFragment.setBottomClickListener(this);
        bottomFragment.setSelected(0);

    }





    @Override
    public void onBottomClick(View v, int position) {
        switch (position) {
            case BottomFragment.MAINPAGE:
                Fragment mainpagetilte = new PublicTitleFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("changetitle", BottomFragment.MAINPAGE);
                mainpagetilte.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.titlefragment, mainpagetilte).commit();
                setChioceItem(0);
                break;

            case BottomFragment.PERSONALCENTER:
                Fragment personalcentertitle = new PublicTitleFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putInt("changetitle", BottomFragment.PERSONALCENTER);
                personalcentertitle.setArguments(bundle3);
                getSupportFragmentManager().beginTransaction().replace(R.id.titlefragment, personalcentertitle).commit();
                setChioceItem(1);
                break;
            default:
                break;
        }
    }

    // 定义一个选中一个item后的处理
    public void setChioceItem(int index) {
        // 重置选项+隐藏所有Fragment
        transaction = fManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (fg1 == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    fg1 = new MainPageFragment();
                    transaction.add(R.id.secondappcontent, fg1);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fg1);
                }
                break;

            case 1:
                if (fg4 == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    fg4 = new PersonalcenterFragment();
                    transaction.add(R.id.secondappcontent, fg4);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fg4);
                }
                break;
        }
        transaction.commit();
    }

    // 隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (fg1 != null) {
            transaction.hide(fg1);
        }

        if (fg4 != null) {
            transaction.hide(fg4);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                @SuppressWarnings("unused")
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}