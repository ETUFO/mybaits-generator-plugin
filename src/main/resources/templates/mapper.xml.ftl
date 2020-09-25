<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper?substring(0,package.Mapper?length-7)+".dao"}.${table.mapperName}">

    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
        <#list table.fields as field>
            <#if field.keyFlag><#--生成主键排在第一位-->
         <id column="${field.name}" property="${field.propertyName}" jdbcType="${field.type?split("(")[0]?upper_case}"/>
            </#if>
        </#list>
        <#list table.commonFields as field><#--生成公共字段 -->
         <result column="${field.name}" property="${field.propertyName}" jdbcType="${field.type?split("(")[0]?upper_case}"/>
        </#list>
        <#list table.fields as field>
            <#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}" jdbcType="${field.type?split("(")[0]?upper_case}"/>
            </#if>
        </#list>
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        <#list table.commonFields as field>
            ${field.name},
        </#list>
        ${table.fieldNames}
    </sql>
</mapper>