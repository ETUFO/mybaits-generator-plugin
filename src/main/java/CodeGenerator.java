import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义imybatisplus代码生成
 * @author wangye
 * @classname CodeGenerator
 * @date 2020/6/19 15:36
 **/
public class CodeGenerator {


    public static void generate(String configFilePath) {
        System.out.println(System.getProperty("user.dir")+"/"+configFilePath);
        Props props= new Props(System.getProperty("user.dir")+"/"+configFilePath);

        String parentPackage = props.getStr("parent.package");
        String parentPath = parentPackage.replace(".", "/")+"/";
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(props.getStr("author"));
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        gc.setSwagger2(props.getBool("swagger2"));
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(props.getStr("jdbc.url"));
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(props.getStr("jdbc.username"));
        dsc.setPassword(props.getStr("jdbc.password"));
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(props.getStr("module.name"));
        pc.setParent(parentPackage);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/java/"+ parentPath + pc.getModuleName()
                        + "/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // 自定义mapper配置
        templatePath = "/templates/mapper.java.ftl";
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 + pc.getModuleName()
                String expand = projectPath + "/src/main/java/"+ parentPath +pc.getModuleName() + "/" + "dao";
                String entityFile = String.format((expand + File.separator + "%s" + ".java"), tableInfo.getMapperName());
                return entityFile;
            }
        });

        if(props.getBool("all.generate")){
            // 自定义controller的代码模板
            templatePath = "/templates/controller.java.ftl";
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 + pc.getModuleName()
                    String expand = projectPath + "/src/main/java/"+ parentPath +pc.getModuleName() + "/" + "controller";
                    String entityFile = String.format((expand + File.separator + "%s" + ".java"), tableInfo.getControllerName());
                    return entityFile;
                }
            });


            // 自定义service的代码模板
            templatePath = "/templates/serviceImpl.java.ftl";
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 + pc.getModuleName()
                    String expand = projectPath + "/src/main/java/"+ parentPath +pc.getModuleName() + "/" + "service";
                    String entityFile = String.format((expand + File.separator + "%s" + ".java"), tableInfo.getServiceImplName().substring(0,tableInfo.getServiceImplName().length()-4));
                    return entityFile;
                }
            });
        }

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //不生成默认的service接口与serviceImpl实现类
        templateConfig.setController(null);
        templateConfig.setXml(null);
        templateConfig.setMapper(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperEntityClass("com.seentao.framework.base.BaseEntity");
        strategy.setSuperControllerClass("com.seentao.framework.base.BaseController");
        strategy.setSuperMapperClass("com.seentao.framework.base.FrameworkBaseMapper");
        strategy.setSuperServiceImplClass("com.seentao.framework.base.BaseServiceImpl");

        strategy.setInclude(props.getStr("table.names").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
