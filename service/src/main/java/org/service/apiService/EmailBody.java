package org.service.apiService;

import org.springframework.stereotype.Service;

@Service
public class EmailBody {

	public static String setEmailBody(String Username, String Password, String Code) {
		return "<html xmlns='http://www.w3.org/1999/xhtml'>"+
				"<head>"+
				  "<meta name='viewport' content='width=device-width, initial-scale=1.0' />"+
				  "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"+
				  "<title>V & Y Soft. Tech. Pvt. Ltd.</title>"+
				  "<style type='text/css' rel='stylesheet' media='all'>"+
				    "*:not(br):not(tr):not(html) {"+
				      "font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;"+
				      "-webkit-box-sizing: border-box;"+
				      "box-sizing: border-box;"+
				    "}"+
				    "body {"+
				      "width: 100% !important;"+
				      "height: 100%;"+
				      "margin: 0;"+
				      "line-height: 1.4;"+
				      "background-color: #F5F7F9;"+
				      "color: #839197;"+
				      "-webkit-text-size-adjust: none;"+
				    "}"+
				    "a {"+
				      "color: #414EF9;"+
				    "}"+
				    ".email-wrapper {"+
				      "width: 100%;"+
				      "margin: 0;"+
				      "padding: 0;"+
				      "background-color: #F5F7F9;"+
				    "}"+
				    ".email-content {"+
				      "width: 100%;"+
				      "margin: 0;"+
				      "padding: 0;"+
				    "}"+
				    ".email-masthead {"+
				      "padding: 25px 0;"+
				      "text-align: center;"+
				    "}"+
				    ".email-masthead_logo {"+
				      "max-width: 400px;"+
				      "border: 0;"+
				    "}"+
				    ".email-masthead_name {"+
				      "font-size: 16px;"+
				      "font-weight: bold;"+
				      "color: #839197;"+
				      "text-decoration: none;"+
				      "text-shadow: 0 1px 0 white;"+
				    "}"+
				    ".email-body {"+
				      "width: 100%;"+
				      "margin: 0;"+
				      "padding: 0;"+
				      "border-top: 1px solid #E7EAEC;"+
				      "border-bottom: 1px solid #E7EAEC;"+
				      "background-color: #FFFFFF;"+
				    "}"+
				    ".email-body_inner {"+
				      "width: 570px;"+
				      "margin: 0 auto;"+
				      "padding: 0;"+
				    "}"+
				    ".email-footer {"+
				      "width: 570px;"+
				      "margin: 0 auto;"+
				      "padding: 0;"+
				      "text-align: center;"+
				    "}"+
				    ".email-footer p {"+
				      "color: #839197;"+
				    "}"+
				    ".body-action {"+
				      "width: 100%;"+
				      "margin: 30px auto;"+
				      "padding: 0;"+
				      "text-align: center;"+
				    "}"+
				    ".body-sub {"+
				      "margin-top: 25px;"+
				      "padding-top: 25px;"+
				      "border-top: 1px solid #E7EAEC;"+
				    "}"+
				    ".content-cell {"+
				      "padding: 35px;"+
				    "}"+
				    ".align-right {"+
				      "text-align: right;"+
				    "}"+
				    "h1 {"+
				      "margin-top: 0;"+
				      "color: #292E31;"+
				      "font-size: 19px;"+
				      "font-weight: bold;"+
				      "text-align: left;"+
				    "}"+
				    "h2 {"+
				      "margin-top: 0;"+
				      "color: #292E31;"+
				      "font-size: 16px;"+
				      "font-weight: bold;"+
				      "text-align: left;"+
				    "}"+
				    "h3 {"+
				      "margin-top: 0;"+
				      "color: #292E31;"+
				      "font-size: 14px;"+
				      "font-weight: bold;"+
				      "text-align: left;"+
				    "}"+
				    "p {"+
				      "margin-top: 0;"+
				      "color: #839197;"+
				      "font-size: 16px;"+
				      "line-height: 1.5em;"+
				      "text-align: left;"+
				    "}"+
				    "p.sub {"+
				      "font-size: 12px;"+
				    "}"+
				    "p.center {"+
				      "text-align: center;"+
				    "}"+
				    ".button {"+
				      "display: inline-block;"+
				      "width: 200px;"+
				      "background-color: #414EF9;"+
				      "border-radius: 3px;"+
				      "color: #ffffff;"+
				      "font-size: 15px;"+
				      "line-height: 45px;"+
				      "text-align: center;"+
				      "text-decoration: none;"+
				      "-webkit-text-size-adjust: none;"+
				      "mso-hide: all;"+
				    "}"+
				    ".button--green {"+
				      "background-color: #28DB67;"+
				    "}"+
				    ".button--red {"+
				      "background-color: #FF3665;"+
				    "}"+
				    ".button--blue {"+
				      "background-color: #414EF9;"+
				    "}"+
				    "@media only screen and (max-width: 600px) {"+
				      ".email-body_inner,"+
				      ".email-footer {"+
				        "width: 100% !important;"+
				      "}"+
				    "}"+
				    "@media only screen and (max-width: 500px) {"+
				      ".button {"+
				        "width: 100% !important;"+
				      "}"+
				    "}"+
				  "</style>"+
				"</head>"+
				"<body>"+
				  "<table class='email-wrapper' width='100%' cellpadding='0' cellspacing='0'>"+
				    "<tr>"+
				      "<td align='center'>"+
				        "<table class='email-content' width='100%' cellpadding='0' cellspacing='0'>"+
				          "<tr>"+
				            "<td class='email-masthead'>"+
				              "<a class='email-masthead_name'>Welcome to V & Y Soft. Tech. Pvt. Ltd. Organizations</a>"+
				            "</td>"+
				          "</tr>"+
				          "<tr>"+
				            "<td class='email-body' width='100%'>"+
				              "<table class='email-body_inner' align='center' width='570' cellpadding='0' cellspacing='0'>"+
				                "<tr>"+
				                  "<td class='content-cell'>"+
				                    "<h1>Verify your Account</h1>"+
				                    "<p>Thanks for signing up for <b>Cloud Hotel Management Desktop App</b> We're excited to have you as an early user.</p>"+
				                    "<table class='body-actio' align='center' width='100%' cellpadding='0' cellspacing='0'>"+
				                      "<tr>"+
				                        "<td align='center'>"+
				                          "<div>"+
				                            "Your Verification Code : <b>"+Code+"</b>"+
				                          "</div>"+
				                        "</td>"+
				                      "</tr>"+
				                    "</table>"+
				                    "<p>"+
				                        "Username: "+Username+ "<br/>"+
				                        "Password: "+Password+"</p>"+
				                    "<p>Thanks,<br>The Development Team</p>"+
				                    "<table class='body-sub'>"+
				                      "<tr>"+
				                        "<td>"+
				                          "<p class='sub'>If you’re having any trouble or issue, you can contact us below contact."+
				                          "</p>"+
				                          "<p class='sub'>"+
				                              "Phone no :+91-8149648134 / +91-8007046661<br/>"+
				                              "Email : v2018y@gmail.com"+
				                        "</p>"+
				                        "</td>"+
				                      "</tr>"+
				                    "</table>"+
				                  "</td>"+
				                "</tr>"+
				              "</table>"+
				            "</td>"+
				          "</tr>"+
				          "<tr>"+
				            "<td>"+
				              "<table class='email-footer' align='center' width='570' cellpadding='0' cellspacing='0'>"+
				                "<tr>"+
				                  "<td class='content-cell'>"+
				                    "<p class='sub center'>"+
				                      "V & Y Soft. Tech. Pvt. Ltd.<br />"+
				                      "Nanaded- Hydernbad Highway,Dhengov,Nanded,MH-431603."+
				                    "</p>"+
				                  "</td>"+
				                "</tr>"+
				              "</table>"+
				            "</td>"+
				          "</tr>"+
				        "</table>"+
				      "</td>"+
				    "</tr>"+
				  "</table>"+
				"</body>"+
				"</html>";
	}
	
}
