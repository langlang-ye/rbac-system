1. 项目使用spring boot 搭建, 数据写入在了 json 文本中. 在 LoginInterceptor 的 preHandle() 方法中
处理header, 解析出来转成 user 对象, 判断用户是否存在, 用户对应的角色是否存在. ExceptionControllerAdvice 
对系统中所有的异常进行处理. 直接运行 Main.java 启动项目后, 可以访问: http://localhost:8080/swagger-ui.html.
在 test 包下的MainTest 里, 包含了添加用户,访问资源的测试和失败的测试用例.
2. 生成的token, 详见 TokenTest. 默认的用户和资源详见 resources 下的json文件.

   User(userId=123456, accountName=admin, role=ADMIN)    eyJ1c2VySWQiOjEyMzQ1NiwiYWNjb3VudE5hbWUiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiJ9
   User(userId=111111, accountName=jack, role=USER)    eyJ1c2VySWQiOjExMTExMSwiYWNjb3VudE5hbWUiOiJqYWNrIiwicm9sZSI6IlVTRVIifQ==
   User(userId=222222, accountName=tom, role=USER)    eyJ1c2VySWQiOjIyMjIyMiwiYWNjb3VudE5hbWUiOiJ0b20iLCJyb2xlIjoiVVNFUiJ9
   User(userId=333333, accountName=carl, role=USER)    eyJ1c2VySWQiOjMzMzMzMywiYWNjb3VudE5hbWUiOiJjYXJsIiwicm9sZSI6IlVTRVIifQ==
   User(userId=999999, accountName=jerry, role=USER)     不存在的用户 eyJ1c2VySWQiOjk5OTk5OSwiYWNjb3VudE5hbWUiOiJqZXJyeSIsInJvbGUiOiJVU0VSIn0=
   User(userId=333333, accountName=jerry, role=AAA)     不存在的角色 eyJ1c2VySWQiOjMzMzMzMywiYWNjb3VudE5hbWUiOiJqZXJyeSIsInJvbGUiOiJBQUEifQ==

 