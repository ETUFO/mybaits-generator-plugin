package ${package.Controller};


import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceImplName?substring(0,(table.serviceImplName)?length-4)};
import ${package.Entity}.${entity};
import com.seentao.framework.result.Result;
<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
 </#if>
import javax.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import lombok.extern.slf4j.Slf4j;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
*
* @author ${author}
* @date ${date}
*/
<#if restControllerStyle>
@Slf4j
  <#if swagger2>
@Api(tags = {"${table.comment!}"})
  </#if>
@RestController
@Validated
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
   <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
   <#else>
public class ${table.controllerName} {
   </#if>

   @Autowired
   private ${table.serviceImplName?substring(0,(table.serviceImplName)?length-4)} ${table.serviceImplName?substring(0,(table.serviceImplName)?length-4)?uncap_first};

   @RequestMapping(value = "/getById", method = RequestMethod.GET)
   <#if swagger2>
   @ApiOperation(value = "简要说明, ${author}, ${date}", notes = "详细说明")
   @ApiImplicitParam(name = "id", value = "主键id", dataType="String",required = true)
   </#if>
   public Result<${entity}> getById(@NotBlank(message="id不能为空") String id){
     return null;
   }
}
</#if>