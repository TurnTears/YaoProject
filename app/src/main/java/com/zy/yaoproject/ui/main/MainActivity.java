package com.zy.yaoproject.ui.main;

import android.support.annotation.IntDef;
import android.widget.FrameLayout;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.widget.BottomBar;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    private SupportFragment[] fragments = new SupportFragment[4];

    public static final int TAB_HOME = 0;
    public static final int TAB_CLASS = 1;
    public static final int TAB_CART = 2;
    public static final int TAB_PERSION = 3;

    @IntDef({TAB_HOME, TAB_CLASS, TAB_CART, TAB_PERSION})
    @Retention(RetentionPolicy.SOURCE)
    private @interface Tabs {
    }

    @Tabs
    private static int nextTab = -1;//切换到指定tab

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initFragment();
        initTab();
    }

    private void initFragment() {
        SupportFragment homeFragment = findFragment(HomeFragment.class);
        if (homeFragment == null) {
            fragments[0] = new HomeFragment();
            fragments[1] = new ClassFragment();
            fragments[2] = new CartFragment();
            fragments[3] = new UserFragment();
            loadMultipleRootFragment(R.id.fragment_container, 0,
                    fragments[0],
                    fragments[1],
                    fragments[2],
                    fragments[3]);
        } else {
            fragments[0] = homeFragment;
            fragments[1] = findFragment(ClassFragment.class);
            fragments[2] = findFragment(CartFragment.class);
            fragments[3] = findFragment(UserFragment.class);
        }
    }

    private void initTab() {
        bottomBar.setOnTabClick((nextPosition, currePosition) ->
                showHideFragment(fragments[nextPosition], fragments[currePosition]));
    }

    /**
     * 切换到指定fragment
     */
    public static void setNextTab(@Tabs int tabPosition) {
        nextTab = tabPosition;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nextTab != -1) {
            showHideFragment(fragments[nextTab]);
            nextTab = -1;
        }
    }
}
