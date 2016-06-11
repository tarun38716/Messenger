package org.tarun.javabrains.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

//Class to tell different params

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("authSessionID") String header,
											@CookieParam("name") String cookie) {
		return "Matrix param: " + matrixParam + " Header param: " + header + " Cookie param: " + cookie;
	}

	
	//http://localhost:8080/messenger/webapi/injectdemo/annotations;param=matrixparam URL for Matrix param
	
/*	MatrixParam - Semicolon after URl similiar to queryparam
	HeaderParam - Custom Key value pairs added to headers
	CookieParam - cookie Parameters
	FormParam - HTML form key value pairs*/
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
		String path = uriInfo.getAbsolutePath().toString();
		uriInfo.getQueryParameters();//Returns map containing all the Query params
		//There are many more methods in uriInfo which contains the details of URI
		System.out.println(headers.getDate());
		//headers contains header details
		String cookies = headers.getCookies().toString();
		//@context annotation is used if we are not sure what is passed through the URI
		return "Path : " + path+" Cookies: " + cookies;
		
		
	}
}
