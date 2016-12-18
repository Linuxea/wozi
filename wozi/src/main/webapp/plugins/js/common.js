/* 2016-12-18 begin  */
//将地址中的参数转化成对象存放
function handleSearchParams(href){
		var paramPair = {};
		var
			a = [],
			b = [],
			c = [];
		a = href.split("?");
		b = a[1].split("&");
		for(let i = 0,j = b.length;i<j;i++) {
			c = b[i].split("=");
			paramPair[c[0]] = c[1];
		}
		return paramPair;
	}
/* 2016-12-18 end  */