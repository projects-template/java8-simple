# 示例工程

## 说明事项

1. controller层接口直接返回业务所需要的结果即可，最终将包装成统一的返回结果，返回实际的最终结构示例为如下结构，data表示业务返回的结果。
    ``` json
   {
        "status": 200,
        "message": "ok",
        "data": [
            {
                "name": "张三",
                "id": 1
            }
        ],
        "timestamp": 1675330849918,
        "success": true
    }
   ```
   业务抛出异常也将被包装，示例如下。
   ``` json
   {
       "status": 500,
       "message": "系统异常",
       "data": null,
       "timestamp": 1675330849918,
       "success": false
   }
   ```
2. 如遇特殊情况不需要包装统一返回结果可以使用 `config.IgnoreResponseAdvice` 注解
3. 自定义业务异常都需要继承 `common.exception.BaseException`
4. 接口默认前缀为 `/api`，不需要额外设置，如 `controller` 配置接口路径为 `/test/user`，实际为 `/api/test/user`
5. 数据库使用 `flyway` 进行数据库版本控制，数据库文件目录为 `/resource/db.migration`中，<br/>
   命名规范：`前缀 + 版本号 + _ +日期 + _ + 顺序 + __ + 描述`, eg: V1.0.0_20230206_0__init.sql,<br/>
   前缀：V 代表版本迁移，U 代表撤销迁移，R 代表可重复迁移<br/>
   版本号： 当前版本为`1.0.0`开始<br/>
   日期： 当前年月日<br/>
   顺序： 当日第一个创建的数据库文件从`0`开始，顺序递增<br/>
   描述： 简单描述数据库文件作用，或者作用的相关模块<br/>
   注意：当前的 `init.sql`
   文件为示例文件，项目有正式数据库文件后，需要将数据库的`flyway`记录删除，记录具体位置为 `flyway_schema_history` 表中。

## 接口文档

访问: [接口文档地址](http://127.0.0.1:8888/api/doc.html) <br>

## 代码提交规范

1. 代码提交前需要进行代码格式化，使用IDEA时，可使用`Save Actions`插件, 安装方式：`IDEA设置 - Plugins - 搜索 - 安装`
2. 提交格式: `提交类型(模块名称)：提交内容描述[task_任务单号]`<br/>
   eg: `feat(user): 新增查询用户列表接口[task_0]`
   提交类型：<br/>
   `feat` - 新增功能(常用)<br/>
   `fix` - 修复bug(常用)<br/>
   `docs` - 仅修改文档<br/>
   `style` - 格式化代码<br/>
3. 功能开发分支创建在`feature`下<br/>
   命名格式：`f-时间戳-功能模块或功能描述`<br/>
   eg: f-20230223-store 或 f-20230223-storeList

## 其他要注意规范

见 钉钉文档 <br/>
[XMWEFUN-JAVA开发规范说明.docx](https://alidocs.dingtalk.com/i/nodes/m0Xw6OYE4D7VLLK19b0dVRq13rbjgPM5?utm_scene=person_space) <br />
[产品研发管理流程说明.docx](https://alidocs.dingtalk.com/i/nodes/jkB7yl4ZK3vV6KrNb4mvJPMX2O6oxqw0?utm_scene=person_space)
