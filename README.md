# LoadAnimTemplate

现在的App或多或少都会用到加载动画，那么如何将多样性的加载动画集成到我们的页面框架中呢？

如果我们在BaseActivity中添加LoadingProgress，那么问题来了，我们怎么在不通过布局文件，
并且不影响子类的情况下，添加LoadingProgress呢？

LoadAnimTemplate就解决了这个问题，而且使用方便简单。

```
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
```


## 博客地址
http://blog.csdn.net/KisenHuang/article/details/70849257