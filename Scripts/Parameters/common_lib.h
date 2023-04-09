login(){
	
	lr_start_transaction("open_home_page");
	
	web_add_auto_header("Sec-Fetch-Site", 
		"none");

	web_add_auto_header("Sec-Fetch-Dest", 
		"document");

	web_add_auto_header("Sec-Fetch-Mode", 
		"navigate");

	web_add_auto_header("Sec-Fetch-User", 
		"?1");

	web_add_auto_header("Upgrade-Insecure-Requests", 
		"1");

	web_add_auto_header("sec-ch-ua", 
		"\"Google Chrome\";v=\"111\", \"Not(A:Brand\";v=\"8\", \"Chromium\";v=\"111\"");

	web_add_auto_header("sec-ch-ua-mobile", 
		"?0");

	web_add_auto_header("sec-ch-ua-platform", 
		"\"Windows\"");
	
	web_reg_find("Text=Web Tours",                                                                          //FIND******************
		LAST);

/*Correlation comment - Do not change!  Original value='136160.07509113HAADHDDpizcftcAccptADVHf' Name ='userSession' Type ='ResponseBased'*/
	web_reg_save_param_attrib(
		"ParamName=userSession",
		"TagName=input",
		"Extract=value",
		"Name=userSession",
		"Type=hidden",
		SEARCH_FILTERS,
		"IgnoreRedirections=No",
		"RequestUrl=*/nav.pl*",
		LAST);

	web_url("WebTours", 
		"URL=http://localhost:1080/WebTours/", 
		"TargetFrame=", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=", 
		"Snapshot=t30.inf", 
		"Mode=HTML", 
		LAST);
	
	lr_end_transaction("open_home_page", LR_AUTO);
	
	lr_think_time(5);

	web_set_sockets_option("SSL_VERSION", "AUTO");
	
	lr_start_transaction("login");
	
	web_reg_find("Text/IC=User password was correct",                                                     //FIND******************
		LAST);

	/*Possible OAUTH authorization was detected. It is recommended to correlate the authorization parameters.*/

	web_add_auto_header("Sec-Fetch-Dest", 
		"frame");

	web_revert_auto_header("Sec-Fetch-User");

	web_revert_auto_header("Upgrade-Insecure-Requests");

	web_add_header("Origin", 
		"http://localhost:1080");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-origin");

	web_image_check("web_image_check",
		"Src=images/webtours.png",                                                                          //FIND******************
		LAST);
	
	web_submit_data("login.pl",
		"Action=http://localhost:1080/cgi-bin/login.pl",
		"Method=POST",
		"TargetFrame=body",
		"RecContentType=text/html",
		"Referer=http://localhost:1080/cgi-bin/nav.pl?in=home",
		"Snapshot=t31.inf",
		"Mode=HTML",
		ITEMDATA,
		"Name=userSession", "Value={userSession}", ENDITEM,
		"Name=username", "Value={login}", ENDITEM,
		"Name=password", "Value={password}", ENDITEM,
		"Name=login.x", "Value=51", ENDITEM,
		"Name=login.y", "Value=5", ENDITEM,
		"Name=JSFormSubmit", "Value=off", ENDITEM,
		LAST);
	
	lr_end_transaction("login", LR_AUTO);
	
	lr_think_time(5);
	
	return 0;
}

logout(){
	
	lr_start_transaction("logout");
	
	web_reg_find("Text/IC=Session ID",                                                                          //FIND******************
		LAST);

	web_revert_auto_header("Sec-Fetch-User");

	web_url("SignOff Button", 
		"URL=http://localhost:1080/cgi-bin/welcome.pl?signOff=1", 
		"TargetFrame=body", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=http://localhost:1080/cgi-bin/nav.pl?page=menu&in=itinerary", 
		"Snapshot=t37.inf", 
		"Mode=HTML", 
		LAST);
	
	lr_end_transaction("logout", LR_AUTO);
	
	return 0;
}