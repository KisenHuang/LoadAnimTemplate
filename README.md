# LoadAnimTemplate

现在的App或多或少都会用到加载动画，那么如何将多样性的加载动画集成到我们的页面框架中呢？

```
public abstract class BaseActivity extends AppCompatActivity {

    private LoadingProgress mProgress;
    private List<View> views = new ArrayList<>();
    private boolean isLoading = false;
    private ViewGroup mContentView;

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //在setContentView() 中所设置布局的父容器的ID 是 android.R.id.content
        mContentView = (ViewGroup) findViewById(android.R.id.content);
        setupLoadView();
    }

    /**
     * 初始化加载动画视图
     * 找到布局中的所有一级子view
     */
    private void setupLoadView() {
        if (mProgress != null)
            return;
        mProgress = new LoadingProgress(this);
        mProgress.setBackgroundColor(Color.WHITE);
        View contentView = mContentView.getChildAt(0);
        if (contentView instanceof ViewGroup) {
            ViewGroup contentGroup = (ViewGroup) contentView;
            for (int i = 1; i < contentGroup.getChildCount(); i++) {
                views.add(contentGroup.getChildAt(i));
            }
        }
        int marginTop = getTitleHeight();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, marginTop, 0, 0);
        mContentView.addView(mProgress, layoutParams);
    }

    /**
     * 开启动画
     */
    protected void openLoadAnim() {
        if (isLoading || mProgress == null)
            return;
        mProgress.show();
        isLoading = true;
    }

    /**
     * 关闭动画
     */
    protected void closeLoadAnim() {
        if (!isLoading || mProgress == null)
            return;
        mProgress.dismiss();
        isLoading = false;
    }

    /**
     * 初始化view
     * 设置view 部分属性(显示隐藏等)
     */
    protected abstract void initView();

    /**
     * 加载数据
     * 在这里调用 openLoadAnim() 方法
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

	/**
     * 返回title高度，防止加载动画格挡标题
     */
    protected abstract int getTitleHeight();

    /**
     * 父容器获取焦点，禁止子控件自动获取焦点
     * 布局中有EditText时，禁止弹出软键盘
     */
    protected void containerFocus() {
        mContentView.getChildAt(0).setFocusable(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeLoadAnim();
        mProgress = null;
        views.clear();
        views = null;
        mContentView = null;
    }
}
```

## 使用方式
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        openLoadAnim();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getTitleHeight() {
        return 0;
    }
}



## 博客地址
http://blog.csdn.net/KisenHuang/article/details/70849257