Tools Used: Eclipse, Tomcat 7, JAX-RS 2.X, JAXB (Java XML Binding)

Steps to deploy on server:
1. Import the project in eclipse as exsisting maven project
2. run the command mvn clean package to build the war file.
3. Deploy the war file on server and start the server

To test the code:
Use google chrome extension POSTMAN to test the webservice.

1. Create Claim (PUT request)

	Sample data: create-claim.xml. Pass this data in the request body
	URL:- http://localhost:8080/ClaimManagement/rest/ClaimService/claims/create
	
2. Read a specific claim (GET request)
	 
	Path Param: Pass the claim number
	URL:- http://localhost:8080/ClaimManagement/rest/ClaimService/claims/read/22c9c23bac142856018ce14a26b6c299
	
3. List of claims by date range (GET request)
	
	Path Param: Pass the start range and end range
	URL:- http://localhost:8080/ClaimManagement/rest/ClaimService/claims/readclaim/2014-07-06/2014-07-10
	
4. Update a claim (POST request)

	Sample data: update-claim.xml. Pass this data in the request body
	URL:- http://localhost:8080/ClaimManagement/rest/ClaimService/claims/update

5. Read a specific vehicle (GET request)
	
	Path Param: Pass the claim number and vin of the vehicle
	URL:- http://localhost:8080/ClaimManagement/rest/ClaimService/claims/read/22c9c23bac142856018ce14a26b6c299/1M8GDM9AXKP042788
	
6. Delete a claim (DELETE request)

	Path Param: Pass the claim number
	URL:- http://localhost:8080/ClaimManagement/rest/ClaimService/claims/delete/22c9c23bac142856018ce14a26b6c300
	