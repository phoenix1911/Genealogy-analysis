// 计算当前日期在本年度的周数  
Date.prototype.getWeekOfYear = function(weekStart) { 
	// weekStart：每周开始于周几：周日：0，周一：1，周二：2 ...，默认为周日  
    weekStart = (weekStart || 0) - 0;  
    if(isNaN(weekStart) || weekStart > 6)  
        weekStart = 0;    
  
    var year = this.getFullYear();  
    var firstDay = new Date(year, 0, 1);  
    var firstWeekDays = 7 - firstDay.getDay() + weekStart;  
    var dayOfYear = (((new Date(year, this.getMonth(), this.getDate())) - firstDay) / (24 * 3600 * 1000)) + 1;  
    return Math.ceil((dayOfYear - firstWeekDays) / 7) + 1;  
}  
  
Date.prototype.format = function(fmt) { 
     var o = { 
        "M+" : this.getMonth()+1,                 //月份 
        "d+" : this.getDate(),                    //日 
        "h+" : this.getHours(),                   //小时 
        "m+" : this.getMinutes(),                 //分 
        "s+" : this.getSeconds(),                 //秒 
        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
        "S"  : this.getMilliseconds()             //毫秒 
    }; 
    if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }
     for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
         }
     }
    return fmt; 
} 


function f_ym(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	return  y+'年'+(m<10?('0'+m):m)+'月';
}

function p_ym(s){
	var reg=/[\u4e00-\u9fa5]/  //利用正则表达式分隔
	var ss = (s.split(reg));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	if (!isNaN(y) && !isNaN(m))
		return new Date(y,m-1);
	else
		return new Date();
}

function f_yw(date){
	var y = date.getFullYear();
	var w = date.getWeekOfYear();
	return  y+'年'+(w<10?('0'+w):w)+'周';
}

function f_yw_(date){
	var y = date.getFullYear();
	var w = date.getWeekOfYear();
	return  y+'-'+(w<10?('0'+w):w);
}

function p_yw(s){
	var reg=/[\u4e00-\u9fa5]/  //利用正则表达式分隔,第0周开始
	var ss = (s.split(reg));
	var y = parseInt(ss[0],10);
	var w = parseInt(ss[1],10);
	if (!isNaN(y) && !isNaN(w)){
		var d = new Date(y,0,1);
		d.setDate(d.getDate() + w * 7 - 7);
	    return d;
	}else
		return new Date();
}

function f_ymd(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return  y+'年'+(m<10?('0'+m):m)+'月'+(d<10?('0'+d):d)+'日';
}

function p_ymd(s){
	var reg=/[\u4e00-\u9fa5]/  //利用正则表达式分隔
	var ss = (s.split(reg));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d))
		return new Date(y,m-1,d);
	else
		return new Date();
}

function f_ymdh(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	var h = date.getHours();
	return  y+'年'+(m<10?('0'+m):m)+'月'+(d<10?('0'+d):d)+'日'+(h<10?('0'+h):h)+'时';
}

function p_ymdh(s){
	var reg=/[\u4e00-\u9fa5]/  //利用正则表达式分隔
	var ss = (s.split(reg));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	var h = parseInt(ss[3],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h))
		return new Date(y,m-1,d,h);
	else
		return new Date();
}

function f_ymdhm(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	var h = date.getHours();
	var mm = Math.floor(date.getMinutes()/10)*10;
	return  y+'年'+(m<10?('0'+m):m)+'月'+(d<10?('0'+d):d)+'日'+(h<10?('0'+h):h)+'时'+(mm<10?('0'+mm):mm)+'分';
}

function p_ymdhm(s){
	var reg=/[\u4e00-\u9fa5]/  //利用正则表达式分隔
	var ss = (s.split(reg));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	var h = parseInt(ss[3],10);
	var mm = parseInt(ss[4],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(mm))
		return new Date(y,m-1,d,h,mm);
	else
		return new Date();
}

//日期-分钟 （n * 10)
function subMinutes(d, n) {
    var t = new Date(d);//复制并操作新对象，避免改动原对象
    t.setMinutes(t.getMinutes() - n * 10);
    return t;
}

//日期-小时
function subHours(d, n) {
    var t = new Date(d);//复制并操作新对象，避免改动原对象
    t.setHours(t.getHours() - n);
    return t;
}

//日期-日
function subDays(d, n) {
    var t = new Date(d);//复制并操作新对象，避免改动原对象
    t.setDate(t.getDate() - n);
    return t;
}

//日期-周
function subWeeks(d, n) {
    var t = new Date(d);
    t.setDate(t.getDate() - 7 * n);
    return t;
}

//日期-月。日对日，若目标月份不存在该日期，则置为最后一日
function subMonths(d, n) {
    var t = new Date(d);
    t.setMonth(t.getMonth() - n);
    if (t.getDate() != d.getDate()) { t.setDate(0); }
    return t;
}
