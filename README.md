# mybatis-generator-plugin
### 使用说明
### 发布到maven中央仓库后，项目插件引入
```   
<plugin>
   <groupId>com.seentao</groupId>
   <artifactId>mybatisplus-generator-plugin</artifactId>
   <version>1.0.0</version>
   <configuration>
        <configFilePath>src/main/resources/generator/mybatisPlusGneratorConfig.properties</configFilePath>
    </configuration>
</plugin>
```
### 配置文件
路径：src/main/resources/generator/mybatisPlusGneratorConfig.properties
内容：
```# 根路径
parent.package = com.xxx.xxx.xxx
# 模块名称
module.name = xxx
# 表名，多个之间逗号分开
table.names = xxxx
# 数据库配置
jdbc.url=jdbc:mysql://localhost:3306/databaseName?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true
jdbc.username=root
jdbc.password=xxx
# 生成swagger注解
swagger2 = true
# true,生成controller，service，mapper，false只生成mapper
all.generate = false
# 作者
author = ETUFO
```
