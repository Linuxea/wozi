#项目从ssh架构转移到jfianl+others中。https://github.com/Linuxea/wozi_jfinal

#毕业设计答辩项目

##毕业设计项目构思
###idea:
完成一个在线云笔记项目，支持分类，排序，查找，贴标签，在线创建，在线编辑，添加附件，积分累积，分享，加好友，建设群，在线聊天的一体化笔记工具，（后期功能可能会扩展）面向广大的师生，提供一个记录与分享的学习工具。

###Action:
<ol>
<li>用户可进行注册登录的操作，填写基本的用户信息。</li>
<li>在线搜索，添加好友，建立群等操作；</li>
<li>在线创建目录，子目录，子子目录等，在线创建，编辑笔记，同时为了每一篇笔记的独立性与识别性，可以打上自定义标签;</li>
<li>在线搜索笔记，按创建日期、修改日期、标签、分类信息查找;</li>
<li>与单个，多个好友之间能够分享笔记，并完成笔记的点评操作;</li>
<li>笔记的导出功能。</li>
<li>开通积分功能，定制积分获取规则，当用户积分达到一定的级别，可以获取更多的服务，比如更大的存储空间，更大的附件上传，更大的好友人数上限等。</li>
</ol>

###todo 具体实现方案有待商榷:
<ul>
<li>本项目将采用基于MVVM架构来实现，具体框架可能是后台strus2+spring+JDBC封装层+前台angular+HTML+CSS</li>
<li>也可能采用SpringMVC+MyBatis+angular+html+css来实现，后台与前端的数据交互格式采用当前的JSON</li>
</ul>


 ![image](https://github.com/Linuxea/wozi/blob/master/wozi/src/main/webapp/%E9%A1%B9%E7%9B%AE%E8%B5%84%E6%96%99/%E5%9B%BE%E7%89%87/main.png)
