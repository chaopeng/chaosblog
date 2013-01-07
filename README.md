ChaosBlog
=====

What is ChaosBlog
----
ChaosBlog是一个类似Jekyll的静态博客输出程序。

文件目录说明
----

├─builder             java写的博客生成程序，需要调用外部markdown引擎
│  
├─publish             输出目录
│  ├─assets           
│  │  ├─chaoscodebox  代码高亮
│  │  ├─css
│  │  ├─img
│  │  ├─js
│  │  └─prettify
│  └─blog             文章存放目录
│      
└─to-be-publish       待输出的博客站点
    ├─navibar         顶部导航栏的页面
    ├─post            待输出的文章
    ├─_component      网页各部分组件
    ├─_layout         输出的布局
    └─_template       输出的模板

How to use
----
* 把文章以md或者html后缀存放在`/to-be-publish/post/`下任意子目录，命名规则为`yyyy-mm-dd-题目(英文).后缀`
* 修改`/to-be-publish/_config.json`中的`inputpath outputpath`到相应的目录，修改`markdownengine`为你喜欢的markdown引擎，修改其他个性化的项目
* 修改`/to-be-publish/_component/`下面你需要修改的组件，你必须要修改的组件是`includefile`里面的评论系统的账号，当然你完全可以修改成你想要的社交化评论系统
* 在`/to-be-publish/`下执行`buildBlog.bat` 如果是linux则执行`buildBlog.sh`
* 把你的/publish 发布到你的站点吧
