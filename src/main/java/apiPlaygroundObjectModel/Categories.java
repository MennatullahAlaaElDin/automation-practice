package apiPlaygroundObjectModel;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONObject;

import com.shaft.api.RestActions;
import com.shaft.api.RestActions.ParametersType;
import com.shaft.api.RestActions.RequestType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Categories {
	// variables
	private final RestActions apiObj;

	// service name
	private final String categories_service = "categories";

	// constructor
	public Categories(RestActions apiObj) {
		this.apiObj = apiObj;
	}

	// keywords
	public Response returnAllCategories() {
		return apiObj.buildNewRequest(categories_service, RequestType.GET)
				.setTargetStatusCode(ApiPlayground.SUCCESS)
				.setContentType(ContentType.JSON)
				.performRequest();
	}
	
	public Response returnCategoriesWithLimitPagination(int limit) {
		List<List<Object>> limitQuery = List.of(Arrays.asList("$limit", limit));
		return apiObj.buildNewRequest(categories_service, RequestType.GET)
				.setTargetStatusCode(ApiPlayground.SUCCESS)
				.setContentType(ContentType.JSON)
				.setParameters(limitQuery, ParametersType.QUERY)
				.performRequest();
	}
	
	public Response returnCategoriesWithSkipPagination(int skip) {
		List<List<Object>> limitQuery = List.of(Arrays.asList("$skip", skip));
		return apiObj.buildNewRequest(categories_service, RequestType.GET)
				.setTargetStatusCode(ApiPlayground.SUCCESS)
				.setContentType(ContentType.JSON)
				.setParameters(limitQuery, ParametersType.QUERY)
				.performRequest();
	}
	
	public Response returnCategoriesErrorHandling(String limit , String skip) {
		List<List<Object>> paginationQuery = Arrays.asList(
				Arrays.asList("$limit", limit),
				Arrays.asList("$skip", skip));
		return apiObj.buildNewRequest(categories_service, RequestType.GET)
				.setTargetStatusCode(ApiPlayground.GENERAL_ERROR)
				.setContentType(ContentType.JSON)
				.setParameters(paginationQuery, ParametersType.QUERY)
				.performRequest();
	}
	

	public Response returnCategoryItemByID(String id) {
		return apiObj.buildNewRequest(categories_service + "/" + id, RequestType.GET)
				.setTargetStatusCode(ApiPlayground.SUCCESS)
				.setContentType(ContentType.JSON)
				.performRequest();
	}

	public Response returnCategoryItemByIDErrorHandling(String id) {
		return apiObj.buildNewRequest(categories_service + "/" + id, RequestType.GET)
				.setTargetStatusCode(ApiPlayground.NOT_FOUND)
				.setContentType(ContentType.JSON)
				.performRequest();
	}

	public Response filterCategoriesByName(String name) {
		List<List<Object>> nameQuery = List.of(Arrays.asList("name[$like]", name));
		return apiObj.buildNewRequest(categories_service, RequestType.GET)
				.setTargetStatusCode(ApiPlayground.SUCCESS)
				.setContentType(ContentType.JSON)
				.setParameters(nameQuery, ParametersType.QUERY)
//				.setUrlArguments("name[$like]=*TV*")
				.performRequest();
	}
	
	public Response filterCategoriesByNameErrorHandling(String name) {
		List<List<Object>> nameQuery = List.of(Arrays.asList("name[$like]", name));
		return apiObj.buildNewRequest(categories_service, RequestType.GET)
				.setTargetStatusCode(ApiPlayground.SUCCESS)
				.setContentType(ContentType.JSON)
				.setParameters(nameQuery, ParametersType.QUERY)
				.performRequest();
	}
	
	@SuppressWarnings("unchecked")
	public Response createNewCategoryItem(String id ,String name) {
		//Create Request JSON Body
				JSONObject createNewItemBody = new JSONObject();
				createNewItemBody.put("id", id + getRandomNumberBetweenTwoValuesAsString(1,50));
				createNewItemBody.put("name", name);
				
				return apiObj.buildNewRequest(categories_service, RequestType.POST)
				.setTargetStatusCode(ApiPlayground.SUCCESS_POST)
				.setContentType(ContentType.JSON)
				.setRequestBody(createNewItemBody)
				.performRequest();
	}
	
	@SuppressWarnings("unchecked")
	public Response createNewCategoryErrorHandling(String id , String name) {
		JSONObject createNewItemBody = new JSONObject();
		createNewItemBody.put("id", id );
		createNewItemBody.put("name", name);
		
		return apiObj.buildNewRequest(categories_service, RequestType.POST)
		.setTargetStatusCode(ApiPlayground.BAD_REQUEST)
		.setContentType(ContentType.JSON)
		.setRequestBody(createNewItemBody)
		.performRequest();
	}
	
	@SuppressWarnings("unchecked")
	public Response updateCategoryByID(String id ,String name) {
		//Create Request JSON Body
				JSONObject updateCategoryByIdBody = new JSONObject();
				updateCategoryByIdBody.put("name", name + "_Updated");
				
				return apiObj.buildNewRequest(categories_service + "/" + id, RequestType.PATCH)
				.setTargetStatusCode(ApiPlayground.SUCCESS)
				.setContentType(ContentType.JSON)
				.setRequestBody(updateCategoryByIdBody)
				.performRequest();
	}
	
	@SuppressWarnings("unchecked")
	public void resetTheUpdatedCategoryByID(String id ,String name) {
		//Create Request JSON Body
				JSONObject updateCategoryByIdBody = new JSONObject();
				updateCategoryByIdBody.put("name", name);
				
				 apiObj.buildNewRequest(categories_service + "/" + id, RequestType.PATCH)
				.setTargetStatusCode(ApiPlayground.SUCCESS)
				.setContentType(ContentType.JSON)
				.setRequestBody(updateCategoryByIdBody)
				.performRequest();
	}
	
	@SuppressWarnings("unchecked")
	public void createNewCategoryItemToBeDeleted(String id ,String name) {
		//Create Request JSON Body
				JSONObject createNewItemBody = new JSONObject();
				createNewItemBody.put("id", id);
				createNewItemBody.put("name", name);
				
				 apiObj.buildNewRequest(categories_service, RequestType.POST)
				.setTargetStatusCode(ApiPlayground.SUCCESS_POST)
				.setContentType(ContentType.JSON)
				.setRequestBody(createNewItemBody)
				.performRequest();
	}
	
	public Response deleteCategoryById(String id) {
		return apiObj.buildNewRequest(categories_service + "/" + id, RequestType.DELETE)
		.setTargetStatusCode(ApiPlayground.SUCCESS)
		.setContentType(ContentType.JSON)
		.performRequest();
	}
	
	public int getDataListLengthWithDefaultPagination(Response response) {
		// Given that using default Skip and Limit 
		return RestActions.getResponseJSONValueAsList(response, "data").size();
	}
	
	public int getDataListLengthWithPagination(Response response) {
		// Given that using default Skip and Limit 
		return RestActions.getResponseJSONValueAsList(response, "data").size();
	}
	
	//Method to generate random integer within min and max values
	public int getRandomNumberBetweenTwoValues(int lowValue, int highValue) {
		return new Random().nextInt(highValue - lowValue) + lowValue;
	    }

	public String getRandomNumberBetweenTwoValuesAsString(int lowValue, int highValue) {
		return Integer.toString(getRandomNumberBetweenTwoValues(lowValue, highValue));
	    }
	

}
