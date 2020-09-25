package ${package.ServiceImpl?substring(0,package.ServiceImpl?length-5)};

import ${package.Entity}.${entity};
import ${package.Mapper?substring(0,package.Mapper?length-7)+".dao"}.${table.mapperName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * ${table.comment!} 服务实现类
 * @author ${author}
 * @date ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName?substring(0,(table.serviceImplName)?length-4)} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(){

}
<#else>
public class ${table.serviceImplName?substring(0,(table.serviceImplName)?length-4)} extends ${superServiceImplClass}<${table.mapperName}, ${entity}>{

}
</#if>
