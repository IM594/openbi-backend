# OpenBI Backend

1) Admin

```
account: admin
password: 123123123
```

2) AWS Database (2 CPU, 4GB RAM, 80GB Storage, Backup up to 2 days with PITR.
   )
```
Database name: bi
Host: openbi-elec5619-openbi.aivencloud.com
Port: 24356
User: avnadmin
Password: AVNS_RzaLxV8hMbvEkpAXNPJ
```

3) API Document

```
http://localhost:8101/api/doc.html
```

# Open BI Backend API Document


**Description**: Open BI Backend API


**HOST**: localhost:8101



[TOC]



# chart-controller


## addChart


**url**:`/api/chart/add`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "chartData": "",
  "chartType": "",
  "goal": "",
  "name": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|chartAddRequest|chartAddRequest|body|true|ChartAddRequest|ChartAddRequest|
|&emsp;&emsp;chartData|||false|string||
|&emsp;&emsp;chartType|||false|string||
|&emsp;&emsp;goal|||false|string||
|&emsp;&emsp;name|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«long»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||integer(int64)|integer(int64)|
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": ""
}
```


## deleteChart


**url**:`/api/chart/delete`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "id": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|deleteRequest|deleteRequest|body|true|DeleteRequest|DeleteRequest|
|&emsp;&emsp;id|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


## editChart


**url**:`/api/chart/edit`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "chartType": "",
  "genChart": "",
  "goal": "",
  "id": 0,
  "name": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|chartEditRequest|chartEditRequest|body|true|ChartEditRequest|ChartEditRequest|
|&emsp;&emsp;chartType|||false|string||
|&emsp;&emsp;genChart|||false|string||
|&emsp;&emsp;goal|||false|string||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


## genChartByAi


**url**:`/api/chart/gen`


**method**:`POST`


**produces**:`multipart/form-data`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file||formData|false|file||
|chartType||query|false|string||
|goal||query|false|string||
|name||query|false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«ChartResponse»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||ChartResponse|ChartResponse|
|&emsp;&emsp;chartId||integer(int64)||
|&emsp;&emsp;genChart||string||
|&emsp;&emsp;genResult||string||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"chartId": 0,
		"genChart": "",
		"genResult": ""
	},
	"message": ""
}
```


## getChartById


**url**:`/api/chart/get`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«Chart»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||Chart|Chart|
|&emsp;&emsp;chartData||string||
|&emsp;&emsp;chartType||string||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;genChart||string||
|&emsp;&emsp;genResult||string||
|&emsp;&emsp;goal||string||
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;isDelete||integer(int32)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;userId||integer(int64)||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"chartData": "",
		"chartType": "",
		"createTime": "",
		"genChart": "",
		"genResult": "",
		"goal": "",
		"id": 0,
		"isDelete": 0,
		"name": "",
		"updateTime": "",
		"userId": 0
	},
	"message": ""
}
```


## getChartByUserId


**url**:`/api/chart/getChartsByUserId`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«List«Chart»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||array|Chart|
|&emsp;&emsp;chartData||string||
|&emsp;&emsp;chartType||string||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;genChart||string||
|&emsp;&emsp;genResult||string||
|&emsp;&emsp;goal||string||
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;isDelete||integer(int32)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;userId||integer(int64)||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": [
		{
			"chartData": "",
			"chartType": "",
			"createTime": "",
			"genChart": "",
			"genResult": "",
			"goal": "",
			"id": 0,
			"isDelete": 0,
			"name": "",
			"updateTime": "",
			"userId": 0
		}
	],
	"message": ""
}
```


## listChartByPage


**url**:`/api/chart/list/chart`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "chartType": "",
  "current": 0,
  "goal": "",
  "id": 0,
  "name": "",
  "pageSize": 0,
  "sortField": "",
  "sortOrder": "",
  "userId": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|chartQueryRequest|chartQueryRequest|body|true|ChartQueryRequest|ChartQueryRequest|
|&emsp;&emsp;chartType|||false|string||
|&emsp;&emsp;current|||false|integer(int64)||
|&emsp;&emsp;goal|||false|string||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;pageSize|||false|integer(int64)||
|&emsp;&emsp;sortField|||false|string||
|&emsp;&emsp;sortOrder|||false|string||
|&emsp;&emsp;userId|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«Page«Chart»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||Page«Chart»|Page«Chart»|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|Chart|
|&emsp;&emsp;&emsp;&emsp;chartData||string||
|&emsp;&emsp;&emsp;&emsp;chartType||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;genChart||string||
|&emsp;&emsp;&emsp;&emsp;genResult||string||
|&emsp;&emsp;&emsp;&emsp;goal||string||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;isDelete||integer||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;userId||integer||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [
			{
				"chartData": "",
				"chartType": "",
				"createTime": "",
				"genChart": "",
				"genResult": "",
				"goal": "",
				"id": 0,
				"isDelete": 0,
				"name": "",
				"updateTime": "",
				"userId": 0
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```


## listMyChartByPage


**url**:`/api/chart/my/list/chart`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "chartType": "",
  "current": 0,
  "goal": "",
  "id": 0,
  "name": "",
  "pageSize": 0,
  "sortField": "",
  "sortOrder": "",
  "userId": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|chartQueryRequest|chartQueryRequest|body|true|ChartQueryRequest|ChartQueryRequest|
|&emsp;&emsp;chartType|||false|string||
|&emsp;&emsp;current|||false|integer(int64)||
|&emsp;&emsp;goal|||false|string||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;pageSize|||false|integer(int64)||
|&emsp;&emsp;sortField|||false|string||
|&emsp;&emsp;sortOrder|||false|string||
|&emsp;&emsp;userId|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«Page«Chart»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||Page«Chart»|Page«Chart»|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|Chart|
|&emsp;&emsp;&emsp;&emsp;chartData||string||
|&emsp;&emsp;&emsp;&emsp;chartType||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;genChart||string||
|&emsp;&emsp;&emsp;&emsp;genResult||string||
|&emsp;&emsp;&emsp;&emsp;goal||string||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;isDelete||integer||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;userId||integer||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [
			{
				"chartData": "",
				"chartType": "",
				"createTime": "",
				"genChart": "",
				"genResult": "",
				"goal": "",
				"id": 0,
				"isDelete": 0,
				"name": "",
				"updateTime": "",
				"userId": 0
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```


## updateChart


**url**:`/api/chart/update`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "chartData": "",
  "chartType": "",
  "createTime": "",
  "genChart": "",
  "genResult": "",
  "goal": "",
  "id": 0,
  "isDelete": 0,
  "name": "",
  "updateTime": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|chartUpdateRequest|chartUpdateRequest|body|true|ChartUpdateRequest|ChartUpdateRequest|
|&emsp;&emsp;chartData|||false|string||
|&emsp;&emsp;chartType|||false|string||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;genChart|||false|string||
|&emsp;&emsp;genResult|||false|string||
|&emsp;&emsp;goal|||false|string||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;isDelete|||false|integer(int32)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;updateTime|||false|string(date-time)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


# email-controller


## sendEmailToAdmin


**url**:`/api/email/sendToAdmin`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userName|userName|query|true|string||
|content|content|query|false|string||
|email|email|query|false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


# post-controller


## addPost


**url**:`/api/post/add`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "content": "",
  "tags": [],
  "title": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postAddRequest|postAddRequest|body|true|PostAddRequest|PostAddRequest|
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;tags|||false|array|string|
|&emsp;&emsp;title|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«long»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||integer(int64)|integer(int64)|
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": ""
}
```


## deletePost


**url**:`/api/post/delete`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "id": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|deleteRequest|deleteRequest|body|true|DeleteRequest|DeleteRequest|
|&emsp;&emsp;id|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


## editPost


**url**:`/api/post/edit`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "content": "",
  "id": 0,
  "tags": [],
  "title": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postEditRequest|postEditRequest|body|true|PostEditRequest|PostEditRequest|
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;tags|||false|array|string|
|&emsp;&emsp;title|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


## getPostVOById


**url**:`/api/post/get/vo`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«PostVO»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||PostVO|PostVO|
|&emsp;&emsp;content||string||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;favourNum||integer(int32)||
|&emsp;&emsp;hasFavour||boolean||
|&emsp;&emsp;hasThumb||boolean||
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;isReport||integer(int32)||
|&emsp;&emsp;tagList||array|string|
|&emsp;&emsp;thumbNum||integer(int32)||
|&emsp;&emsp;title||string||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;user||UserVO|UserVO|
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;userAvatar||string||
|&emsp;&emsp;&emsp;&emsp;userEmail||string||
|&emsp;&emsp;&emsp;&emsp;userName||string||
|&emsp;&emsp;&emsp;&emsp;userProfile||string||
|&emsp;&emsp;&emsp;&emsp;userRole||string||
|&emsp;&emsp;userId||integer(int64)||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"content": "",
		"createTime": "",
		"favourNum": 0,
		"hasFavour": true,
		"hasThumb": true,
		"id": 0,
		"isReport": 0,
		"tagList": [],
		"thumbNum": 0,
		"title": "",
		"updateTime": "",
		"user": {
			"createTime": "",
			"id": 0,
			"userAvatar": "",
			"userEmail": "",
			"userName": "",
			"userProfile": "",
			"userRole": ""
		},
		"userId": 0
	},
	"message": ""
}
```


## listPostVOByPage


**url**:`/api/post/list/page/vo`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "content": "",
  "current": 0,
  "favourUserId": 0,
  "id": 0,
  "isReport": 0,
  "notId": 0,
  "orTags": [],
  "pageSize": 0,
  "searchText": "",
  "sortField": "",
  "sortOrder": "",
  "tags": [],
  "title": "",
  "userId": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postQueryRequest|postQueryRequest|body|true|PostQueryRequest|PostQueryRequest|
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;current|||false|integer(int64)||
|&emsp;&emsp;favourUserId|||false|integer(int64)||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;isReport|||false|integer(int32)||
|&emsp;&emsp;notId|||false|integer(int64)||
|&emsp;&emsp;orTags|||false|array|string|
|&emsp;&emsp;pageSize|||false|integer(int64)||
|&emsp;&emsp;searchText|||false|string||
|&emsp;&emsp;sortField|||false|string||
|&emsp;&emsp;sortOrder|||false|string||
|&emsp;&emsp;tags|||false|array|string|
|&emsp;&emsp;title|||false|string||
|&emsp;&emsp;userId|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«Page«PostVO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||Page«PostVO»|Page«PostVO»|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|PostVO|
|&emsp;&emsp;&emsp;&emsp;content||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;favourNum||integer||
|&emsp;&emsp;&emsp;&emsp;hasFavour||boolean||
|&emsp;&emsp;&emsp;&emsp;hasThumb||boolean||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;isReport||integer||
|&emsp;&emsp;&emsp;&emsp;tagList||array|string|
|&emsp;&emsp;&emsp;&emsp;thumbNum||integer||
|&emsp;&emsp;&emsp;&emsp;title||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;user||UserVO|UserVO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userAvatar||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userEmail||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userName||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userProfile||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userRole||string||
|&emsp;&emsp;&emsp;&emsp;userId||integer||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [
			{
				"content": "",
				"createTime": "",
				"favourNum": 0,
				"hasFavour": true,
				"hasThumb": true,
				"id": 0,
				"isReport": 0,
				"tagList": [],
				"thumbNum": 0,
				"title": "",
				"updateTime": "",
				"user": {
					"createTime": "",
					"id": 0,
					"userAvatar": "",
					"userEmail": "",
					"userName": "",
					"userProfile": "",
					"userRole": ""
				},
				"userId": 0
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```


## listMyPostVOByPage


**url**:`/api/post/my/list/page/vo`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "content": "",
  "current": 0,
  "favourUserId": 0,
  "id": 0,
  "isReport": 0,
  "notId": 0,
  "orTags": [],
  "pageSize": 0,
  "searchText": "",
  "sortField": "",
  "sortOrder": "",
  "tags": [],
  "title": "",
  "userId": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postQueryRequest|postQueryRequest|body|true|PostQueryRequest|PostQueryRequest|
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;current|||false|integer(int64)||
|&emsp;&emsp;favourUserId|||false|integer(int64)||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;isReport|||false|integer(int32)||
|&emsp;&emsp;notId|||false|integer(int64)||
|&emsp;&emsp;orTags|||false|array|string|
|&emsp;&emsp;pageSize|||false|integer(int64)||
|&emsp;&emsp;searchText|||false|string||
|&emsp;&emsp;sortField|||false|string||
|&emsp;&emsp;sortOrder|||false|string||
|&emsp;&emsp;tags|||false|array|string|
|&emsp;&emsp;title|||false|string||
|&emsp;&emsp;userId|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«Page«PostVO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||Page«PostVO»|Page«PostVO»|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|PostVO|
|&emsp;&emsp;&emsp;&emsp;content||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;favourNum||integer||
|&emsp;&emsp;&emsp;&emsp;hasFavour||boolean||
|&emsp;&emsp;&emsp;&emsp;hasThumb||boolean||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;isReport||integer||
|&emsp;&emsp;&emsp;&emsp;tagList||array|string|
|&emsp;&emsp;&emsp;&emsp;thumbNum||integer||
|&emsp;&emsp;&emsp;&emsp;title||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;user||UserVO|UserVO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userAvatar||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userEmail||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userName||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userProfile||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userRole||string||
|&emsp;&emsp;&emsp;&emsp;userId||integer||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [
			{
				"content": "",
				"createTime": "",
				"favourNum": 0,
				"hasFavour": true,
				"hasThumb": true,
				"id": 0,
				"isReport": 0,
				"tagList": [],
				"thumbNum": 0,
				"title": "",
				"updateTime": "",
				"user": {
					"createTime": "",
					"id": 0,
					"userAvatar": "",
					"userEmail": "",
					"userName": "",
					"userProfile": "",
					"userRole": ""
				},
				"userId": 0
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```


## reportPost


**url**:`/api/post/report`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "id": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postReportRequest|postReportRequest|body|true|PostReportRequest|PostReportRequest|
|&emsp;&emsp;id|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


## updatePost


**url**:`/api/post/update`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "content": "",
  "id": 0,
  "tags": [],
  "title": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postUpdateRequest|postUpdateRequest|body|true|PostUpdateRequest|PostUpdateRequest|
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;tags|||false|array|string|
|&emsp;&emsp;title|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


# post-favour-controller


## doPostFavour


**url**:`/api/post_favour/`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "postId": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postFavourAddRequest|postFavourAddRequest|body|true|PostFavourAddRequest|PostFavourAddRequest|
|&emsp;&emsp;postId|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«int»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||integer(int32)|integer(int32)|
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": ""
}
```


## listFavourPostByPage


**url**:`/api/post_favour/list/page`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "current": 0,
  "pageSize": 0,
  "postQueryRequest": {
    "content": "",
    "current": 0,
    "favourUserId": 0,
    "id": 0,
    "isReport": 0,
    "notId": 0,
    "orTags": [],
    "pageSize": 0,
    "searchText": "",
    "sortField": "",
    "sortOrder": "",
    "tags": [],
    "title": "",
    "userId": 0
  },
  "sortField": "",
  "sortOrder": "",
  "userId": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postFavourQueryRequest|postFavourQueryRequest|body|true|PostFavourQueryRequest|PostFavourQueryRequest|
|&emsp;&emsp;current|||false|integer(int64)||
|&emsp;&emsp;pageSize|||false|integer(int64)||
|&emsp;&emsp;postQueryRequest|||false|PostQueryRequest|PostQueryRequest|
|&emsp;&emsp;&emsp;&emsp;content|||false|string||
|&emsp;&emsp;&emsp;&emsp;current|||false|integer||
|&emsp;&emsp;&emsp;&emsp;favourUserId|||false|integer||
|&emsp;&emsp;&emsp;&emsp;id|||false|integer||
|&emsp;&emsp;&emsp;&emsp;isReport|||false|integer||
|&emsp;&emsp;&emsp;&emsp;notId|||false|integer||
|&emsp;&emsp;&emsp;&emsp;orTags|||false|array|string|
|&emsp;&emsp;&emsp;&emsp;pageSize|||false|integer||
|&emsp;&emsp;&emsp;&emsp;searchText|||false|string||
|&emsp;&emsp;&emsp;&emsp;sortField|||false|string||
|&emsp;&emsp;&emsp;&emsp;sortOrder|||false|string||
|&emsp;&emsp;&emsp;&emsp;tags|||false|array|string|
|&emsp;&emsp;&emsp;&emsp;title|||false|string||
|&emsp;&emsp;&emsp;&emsp;userId|||false|integer||
|&emsp;&emsp;sortField|||false|string||
|&emsp;&emsp;sortOrder|||false|string||
|&emsp;&emsp;userId|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«Page«PostVO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||Page«PostVO»|Page«PostVO»|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|PostVO|
|&emsp;&emsp;&emsp;&emsp;content||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;favourNum||integer||
|&emsp;&emsp;&emsp;&emsp;hasFavour||boolean||
|&emsp;&emsp;&emsp;&emsp;hasThumb||boolean||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;isReport||integer||
|&emsp;&emsp;&emsp;&emsp;tagList||array|string|
|&emsp;&emsp;&emsp;&emsp;thumbNum||integer||
|&emsp;&emsp;&emsp;&emsp;title||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;user||UserVO|UserVO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userAvatar||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userEmail||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userName||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userProfile||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userRole||string||
|&emsp;&emsp;&emsp;&emsp;userId||integer||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [
			{
				"content": "",
				"createTime": "",
				"favourNum": 0,
				"hasFavour": true,
				"hasThumb": true,
				"id": 0,
				"isReport": 0,
				"tagList": [],
				"thumbNum": 0,
				"title": "",
				"updateTime": "",
				"user": {
					"createTime": "",
					"id": 0,
					"userAvatar": "",
					"userEmail": "",
					"userName": "",
					"userProfile": "",
					"userRole": ""
				},
				"userId": 0
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```


## listMyFavourPostByPage


**url**:`/api/post_favour/my/list/page`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "content": "",
  "current": 0,
  "favourUserId": 0,
  "id": 0,
  "isReport": 0,
  "notId": 0,
  "orTags": [],
  "pageSize": 0,
  "searchText": "",
  "sortField": "",
  "sortOrder": "",
  "tags": [],
  "title": "",
  "userId": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postQueryRequest|postQueryRequest|body|true|PostQueryRequest|PostQueryRequest|
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;current|||false|integer(int64)||
|&emsp;&emsp;favourUserId|||false|integer(int64)||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;isReport|||false|integer(int32)||
|&emsp;&emsp;notId|||false|integer(int64)||
|&emsp;&emsp;orTags|||false|array|string|
|&emsp;&emsp;pageSize|||false|integer(int64)||
|&emsp;&emsp;searchText|||false|string||
|&emsp;&emsp;sortField|||false|string||
|&emsp;&emsp;sortOrder|||false|string||
|&emsp;&emsp;tags|||false|array|string|
|&emsp;&emsp;title|||false|string||
|&emsp;&emsp;userId|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«Page«PostVO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||Page«PostVO»|Page«PostVO»|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|PostVO|
|&emsp;&emsp;&emsp;&emsp;content||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;favourNum||integer||
|&emsp;&emsp;&emsp;&emsp;hasFavour||boolean||
|&emsp;&emsp;&emsp;&emsp;hasThumb||boolean||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;isReport||integer||
|&emsp;&emsp;&emsp;&emsp;tagList||array|string|
|&emsp;&emsp;&emsp;&emsp;thumbNum||integer||
|&emsp;&emsp;&emsp;&emsp;title||string||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;user||UserVO|UserVO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userAvatar||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userEmail||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userName||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userProfile||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userRole||string||
|&emsp;&emsp;&emsp;&emsp;userId||integer||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [
			{
				"content": "",
				"createTime": "",
				"favourNum": 0,
				"hasFavour": true,
				"hasThumb": true,
				"id": 0,
				"isReport": 0,
				"tagList": [],
				"thumbNum": 0,
				"title": "",
				"updateTime": "",
				"user": {
					"createTime": "",
					"id": 0,
					"userAvatar": "",
					"userEmail": "",
					"userName": "",
					"userProfile": "",
					"userRole": ""
				},
				"userId": 0
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```


## searchFavour


**url**:`/api/post_favour/searchFavour`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "postId": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postFavourAddRequest|postFavourAddRequest|body|true|PostFavourAddRequest|PostFavourAddRequest|
|&emsp;&emsp;postId|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«int»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||integer(int32)|integer(int32)|
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": ""
}
```


# post-thumb-controller


## doThumb


**url**:`/api/post_thumb`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "postId": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postThumbAddRequest|postThumbAddRequest|body|true|PostThumbAddRequest|PostThumbAddRequest|
|&emsp;&emsp;postId|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«int»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||integer(int32)|integer(int32)|
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": ""
}
```


## searchThumb


**url**:`/api/search_thumb`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "postId": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postThumbAddRequest|postThumbAddRequest|body|true|PostThumbAddRequest|PostThumbAddRequest|
|&emsp;&emsp;postId|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«int»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||integer(int32)|integer(int32)|
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": ""
}
```


# user-controller


## addUser


**url**:`/api/user/add`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "userAccount": "",
  "userAvatar": "",
  "userName": "",
  "userRole": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userAddRequest|userAddRequest|body|true|UserAddRequest|UserAddRequest|
|&emsp;&emsp;userAccount|||false|string||
|&emsp;&emsp;userAvatar|||false|string||
|&emsp;&emsp;userName|||false|string||
|&emsp;&emsp;userRole|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«long»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||integer(int64)|integer(int64)|
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": ""
}
```


## banUser


**url**:`/api/user/ban`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "id": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|banRequest|banRequest|body|true|DeleteRequest|DeleteRequest|
|&emsp;&emsp;id|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


## deleteUser


**url**:`/api/user/delete`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "id": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|deleteRequest|deleteRequest|body|true|DeleteRequest|DeleteRequest|
|&emsp;&emsp;id|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


## getUserById


**url**:`/api/user/get`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«User»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||User|User|
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;isDelete||integer(int32)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;userAccount||string||
|&emsp;&emsp;userAvatar||string||
|&emsp;&emsp;userEmail||string||
|&emsp;&emsp;userName||string||
|&emsp;&emsp;userPassword||string||
|&emsp;&emsp;userProfile||string||
|&emsp;&emsp;userRole||string||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"id": 0,
		"isDelete": 0,
		"updateTime": "",
		"userAccount": "",
		"userAvatar": "",
		"userEmail": "",
		"userName": "",
		"userPassword": "",
		"userProfile": "",
		"userRole": ""
	},
	"message": ""
}
```


## getLoginUser


**url**:`/api/user/get/login`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


暂无


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«LoginUserVO»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||LoginUserVO|LoginUserVO|
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;userAvatar||string||
|&emsp;&emsp;userEmail||string||
|&emsp;&emsp;userName||string||
|&emsp;&emsp;userProfile||string||
|&emsp;&emsp;userRole||string||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"id": 0,
		"updateTime": "",
		"userAvatar": "",
		"userEmail": "",
		"userName": "",
		"userProfile": "",
		"userRole": ""
	},
	"message": ""
}
```


## getUserVOById


**url**:`/api/user/get/vo`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«UserVO»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||UserVO|UserVO|
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;userAvatar||string||
|&emsp;&emsp;userEmail||string||
|&emsp;&emsp;userName||string||
|&emsp;&emsp;userProfile||string||
|&emsp;&emsp;userRole||string||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"id": 0,
		"userAvatar": "",
		"userEmail": "",
		"userName": "",
		"userProfile": "",
		"userRole": ""
	},
	"message": ""
}
```


## listUserByPage


**url**:`/api/user/list/page`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "current": 0,
  "id": 0,
  "pageSize": 0,
  "sortField": "",
  "sortOrder": "",
  "userName": "",
  "userProfile": "",
  "userRole": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userQueryRequest|userQueryRequest|body|true|UserQueryRequest|UserQueryRequest|
|&emsp;&emsp;current|||false|integer(int64)||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;pageSize|||false|integer(int64)||
|&emsp;&emsp;sortField|||false|string||
|&emsp;&emsp;sortOrder|||false|string||
|&emsp;&emsp;userName|||false|string||
|&emsp;&emsp;userProfile|||false|string||
|&emsp;&emsp;userRole|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«Page«User»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||Page«User»|Page«User»|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|User|
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;isDelete||integer||
|&emsp;&emsp;&emsp;&emsp;updateTime||string||
|&emsp;&emsp;&emsp;&emsp;userAccount||string||
|&emsp;&emsp;&emsp;&emsp;userAvatar||string||
|&emsp;&emsp;&emsp;&emsp;userEmail||string||
|&emsp;&emsp;&emsp;&emsp;userName||string||
|&emsp;&emsp;&emsp;&emsp;userPassword||string||
|&emsp;&emsp;&emsp;&emsp;userProfile||string||
|&emsp;&emsp;&emsp;&emsp;userRole||string||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"id": 0,
				"isDelete": 0,
				"updateTime": "",
				"userAccount": "",
				"userAvatar": "",
				"userEmail": "",
				"userName": "",
				"userPassword": "",
				"userProfile": "",
				"userRole": ""
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```


## listUserVOByPage


**url**:`/api/user/list/page/vo`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "current": 0,
  "id": 0,
  "pageSize": 0,
  "sortField": "",
  "sortOrder": "",
  "userName": "",
  "userProfile": "",
  "userRole": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userQueryRequest|userQueryRequest|body|true|UserQueryRequest|UserQueryRequest|
|&emsp;&emsp;current|||false|integer(int64)||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;pageSize|||false|integer(int64)||
|&emsp;&emsp;sortField|||false|string||
|&emsp;&emsp;sortOrder|||false|string||
|&emsp;&emsp;userName|||false|string||
|&emsp;&emsp;userProfile|||false|string||
|&emsp;&emsp;userRole|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«Page«UserVO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||Page«UserVO»|Page«UserVO»|
|&emsp;&emsp;countId||string||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;pages||integer(int64)||
|&emsp;&emsp;records||array|UserVO|
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;userAvatar||string||
|&emsp;&emsp;&emsp;&emsp;userEmail||string||
|&emsp;&emsp;&emsp;&emsp;userName||string||
|&emsp;&emsp;&emsp;&emsp;userProfile||string||
|&emsp;&emsp;&emsp;&emsp;userRole||string||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"countId": "",
		"current": 0,
		"maxLimit": 0,
		"optimizeCountSql": true,
		"orders": [
			{
				"asc": true,
				"column": ""
			}
		],
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"id": 0,
				"userAvatar": "",
				"userEmail": "",
				"userName": "",
				"userProfile": "",
				"userRole": ""
			}
		],
		"searchCount": true,
		"size": 0,
		"total": 0
	},
	"message": ""
}
```


## userLogin


**url**:`/api/user/login`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "userAccount": "",
  "userPassword": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userLoginRequest|userLoginRequest|body|true|UserLoginRequest|UserLoginRequest|
|&emsp;&emsp;userAccount|||false|string||
|&emsp;&emsp;userPassword|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«LoginUserVO»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||LoginUserVO|LoginUserVO|
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;userAvatar||string||
|&emsp;&emsp;userEmail||string||
|&emsp;&emsp;userName||string||
|&emsp;&emsp;userProfile||string||
|&emsp;&emsp;userRole||string||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"id": 0,
		"updateTime": "",
		"userAvatar": "",
		"userEmail": "",
		"userName": "",
		"userProfile": "",
		"userRole": ""
	},
	"message": ""
}
```


## userLogout


**url**:`/api/user/logout`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Params**:


暂无


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


## userRegister


**url**:`/api/user/register`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "checkPassword": "",
  "userAccount": "",
  "userPassword": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userRegisterRequest|userRegisterRequest|body|true|UserRegisterRequest|UserRegisterRequest|
|&emsp;&emsp;checkPassword|||false|string||
|&emsp;&emsp;userAccount|||false|string||
|&emsp;&emsp;userPassword|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«long»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||integer(int64)|integer(int64)|
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": ""
}
```


## unbanUser


**url**:`/api/user/unban`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "id": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|unbanRequest|unbanRequest|body|true|DeleteRequest|DeleteRequest|
|&emsp;&emsp;id|||false|integer(int64)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


## updateUser


**url**:`/api/user/update`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "id": 0,
  "userAvatar": "",
  "userName": "",
  "userProfile": "",
  "userRole": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userUpdateRequest|userUpdateRequest|body|true|UserUpdateRequest|UserUpdateRequest|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;userAvatar|||false|string||
|&emsp;&emsp;userName|||false|string||
|&emsp;&emsp;userProfile|||false|string||
|&emsp;&emsp;userRole|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```


## updateMyUser


**url**:`/api/user/update/my`


**method**:`POST`


**produces**:`application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "userAvatar": "",
  "userEmail": "",
  "userName": "",
  "userProfile": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userUpdateMyRequest|userUpdateMyRequest|body|true|UserUpdateMyRequest|UserUpdateMyRequest|
|&emsp;&emsp;userAvatar|||false|string||
|&emsp;&emsp;userEmail|||false|string||
|&emsp;&emsp;userName|||false|string||
|&emsp;&emsp;userProfile|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|BaseResponse«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||boolean||
|message||string||


**Response Example**:
```javascript
{
	"code": 0,
	"data": true,
	"message": ""
}
```
