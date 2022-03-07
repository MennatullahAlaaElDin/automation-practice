package apiPlaygroundTests;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.tools.io.JSONFileManager;
import com.shaft.validation.Validations;
import apiPlaygroundObjectModel.ApiPlayground;
import apiPlaygroundObjectModel.Categories;
import io.restassured.response.Response;

public class CategoriesServiceTests {
	private final File testJSONSchema = new File(System.getProperty("testJSONFolderPath") +"api-playground-schemas");
	private final JSONFileManager testData = new JSONFileManager(System.getProperty("testDataFolderPath") + "API-playground_TestData.json");

	private RestActions apiObj;
	private Categories categoriesApi;
	
	
	@BeforeClass
	public void beforeClass() {
		apiObj = DriverFactory.getAPIDriver(ApiPlayground.BASE_URL);
		categoriesApi = new Categories(apiObj);
		//Authentication Request if any
		//Common authentication header if any
	}	

	@Test(description = "TC_01.01 Check the response when return all items")
	public void checkTheResponseWhenReturnAllCategories() {
		
		Response getAllItemsResponse = categoriesApi.returnAllCategories();
		
		var dataListLength = categoriesApi.getDataListLengthWithDefaultPagination(getAllItemsResponse);
		
		// Use soft assertion
		Validations.verifyThat().response(getAllItemsResponse)
		.matchesSchema(testJSONSchema + "/get_all_categories_response_schema.json")
		.withCustomReportMessage("data is retrieved successfully and JSON response schema is valid")
		.perform();
		
		Validations.verifyThat().response(getAllItemsResponse)
		.extractedJsonValue("limit").isEqualTo(testData.getTestData("defaultLimit"))
		.withCustomReportMessage("Default limit is 10")
		.perform();
		
		Validations.verifyThat().response(getAllItemsResponse)
		.extractedJsonValue("skip").isEqualTo(testData.getTestData("defaultSkip"))
		.withCustomReportMessage("Default skip is 0")
		.perform();
		
		Validations.verifyThat().number(dataListLength).isEqualTo(Integer.parseInt(testData.getTestData("defaultLimit")))
		.withCustomReportMessage("Number of retrieved categories are limited to 10 By Default")
		.perform();
		
	}
	
	@Test(description = "TC_01.02 Case B: Check the number of categories data list when limit value is zero")
	public void checkTheResponseWhenLimitEqualZero() {
		
		Response limitedResponse = categoriesApi.returnCategoriesWithLimitPagination(Integer.parseInt(testData.getTestData("minLimit")));
		
		var dataListLength = categoriesApi.getDataListLengthWithDefaultPagination(limitedResponse);
		
		// Use soft assertion
		Validations.verifyThat().response(limitedResponse)
		.matchesSchema(testJSONSchema + "/get_all_categories_response_schema.json")
		.withCustomReportMessage("data is retrieved successfully and JSON response schema is valid")
		.perform();
		
		Validations.verifyThat().response(limitedResponse)
		.extractedJsonValue("limit").isEqualTo(testData.getTestData("minLimit"))
		.withCustomReportMessage("Limit is displayed in response")
		.perform();
				
		Validations.verifyThat().number(dataListLength).isEqualTo(Integer.parseInt(testData.getTestData("minLimit")))
		.withCustomReportMessage("Data List is empty")
		.perform();
	}
	
	@Test(description = "TC_01.02 Case C: Check the number of categories data list when limit value exceed max value")
	public void checkTheResponseWhenLimitExceedMaxValue() {
		
		Response limitedResponse = categoriesApi.returnCategoriesWithLimitPagination(Integer.parseInt(testData.getTestData("outOfRangLimit")));
		
		var dataListLength = categoriesApi.getDataListLengthWithDefaultPagination(limitedResponse);
		
		// Use soft assertion
		Validations.verifyThat().response(limitedResponse)
		.matchesSchema(testJSONSchema + "/get_all_categories_response_schema.json")
		.withCustomReportMessage("data is retrieved successfully and JSON response schema is valid")
		.perform();
		
		Validations.verifyThat().response(limitedResponse)
		.extractedJsonValue("limit").isEqualTo(testData.getTestData("maxLimit"))
		.withCustomReportMessage("Limit is displayed in response with max value")
		.perform();
				
		Validations.verifyThat().number(dataListLength).isEqualTo(Integer.parseInt(testData.getTestData("maxLimit")))
		.withCustomReportMessage("Number of retrieved categories are limited to Max value")
		.perform();
	}
	
	@Test(description = "TC_01.02 Case D: Check the number of categories data list when limit value is -ve")
	public void checkTheResponseWhenLimitValueIsNegative() {
		
		Response limitedResponse = categoriesApi.returnCategoriesWithLimitPagination(Integer.parseInt(testData.getTestData("-veLimit")));
		
		var dataListLength = categoriesApi.getDataListLengthWithDefaultPagination(limitedResponse);
		
		// Use soft assertion
		Validations.verifyThat().response(limitedResponse)
		.matchesSchema(testJSONSchema + "/get_all_categories_response_schema.json")
		.withCustomReportMessage("data is retrieved successfully and JSON response schema is valid")
		.perform();
		
		Validations.verifyThat().response(limitedResponse)
		.extractedJsonValue("limit").isEqualTo(Integer.toString(Math.abs(Integer.parseInt(testData.getTestData("-veLimit")))))
		.withCustomReportMessage("Limit is translated into positive")
		.perform();
				
		Validations.verifyThat().number(dataListLength).isEqualTo(Math.abs(Integer.parseInt(testData.getTestData("-veLimit"))))
		.withCustomReportMessage("Number of retrieved categories are equal to +ve limit value")
		.perform();
	}
	
	@Test(description = "TC_01.03 Case E: Check the response when skip value is -ve")
	public void checkTheResponseWhenSkipValueIsNegative() {
		
		Response skippedResponse = categoriesApi.returnCategoriesWithSkipPagination(Integer.parseInt(testData.getTestData("-veSkip")));
				
		// Use soft assertion
		Validations.verifyThat().response(skippedResponse)
		.matchesSchema(testJSONSchema + "/get_all_categories_response_schema.json")
		.withCustomReportMessage("data is retrieved successfully and JSON response schema is valid")
		.perform();
		
		Validations.verifyThat().response(skippedResponse)
		.extractedJsonValue("skip").isEqualTo(Integer.toString(Math.abs(Integer.parseInt(testData.getTestData("-veSkip")))))
		.withCustomReportMessage("Skip value is translated into positive")
		.perform();
	}
	
	@Test(description = "TC_01.04 Check error handling with GET method using invalid query parameters")
	public void checkTheResponseWhenReturnCategoriesWithInvalidQueryParameters() {
		
		Response getAllItemsResponse = categoriesApi.returnCategoriesErrorHandling(testData.getTestData("inValidLimitFormat")
				, testData.getTestData("emptySkip"));
		
		Validations.verifyThat().response(getAllItemsResponse)
		.extractedJsonValue("name").isEqualTo("GeneralError")
		.withCustomReportMessage("Error Name should be : NotFound")
		.perform();
		
		Validations.verifyThat().response(getAllItemsResponse)
		.extractedJsonValue("message").isEqualTo("SQLITE_ERROR: no such column: NaN")
		.withCustomReportMessage("Error Message should be SQLITE_ERROR: no such column: NaN")
		.perform();
	}

	@Test(description = "TC_02.01 Check the response when select category item based on id")
	public void checkTheResponseWhenReturnCategoryById() {
		
		Response getItemByIdResponse = categoriesApi.returnCategoryItemByID(testData.getTestData("existValidID"));
		
		Validations.verifyThat().response(getItemByIdResponse)
		.matchesSchema(testJSONSchema + "/get_category_based_on_id_response_schema.json")
		.withCustomReportMessage("data is retrieved successfully and JSON response schema is valid")
		.perform();
		
		Validations.verifyThat().response(getItemByIdResponse)
		.extractedJsonValue("id").isEqualTo(testData.getTestData("existValidID"))
		.withCustomReportMessage("Response contains correct Item id")
		.perform();
	}
	
	@Test(description = "TC_02.02 Check error handling  when select item based on not existed id")
	public void checkTheResponseWhenReturnCategoryByInvalidId() {
		
		Response getItemByIdResponse = categoriesApi.returnCategoryItemByIDErrorHandling(testData.getTestData("inValidID"));
		
		Validations.verifyThat().response(getItemByIdResponse)
		.extractedJsonValue("name").isEqualTo("NotFound")
		.withCustomReportMessage("Error Name should be : NotFound")
		.perform();
		
		Validations.verifyThat().response(getItemByIdResponse)
		.extractedJsonValue("message").isEqualTo("No record found for id '"+testData.getTestData("inValidID")+"'")
		.withCustomReportMessage("Error Message should be No record found for id")
		.perform();
	}
	

	@Test(description = "TC_03.01 Check the response when filter items based on name")
	public void checkTheResponseWhenFilterCategoriesByName() {
		Response filterByNameResponse = categoriesApi.filterCategoriesByName(testData.getTestData("validName"));
		
		Validations.verifyThat().response(filterByNameResponse)
		.matchesSchema(testJSONSchema + "/filter_categories_based_on_name_response_schema.json")
		.withCustomReportMessage("data is retrieved successfully and JSON response schema is valid")
		.perform();
	}
	
	@Test(description = "TC_03.02 Check error handling  when filter items based on not existed name")
	public void checkTheResponseWhenFilterCategoriesByInvalidName() {
		
		Response filterByNameResponse = categoriesApi.filterCategoriesByNameErrorHandling(testData.getTestData("inValidName"));
		
		Validations.verifyThat().response(filterByNameResponse)
		.matchesSchema(testJSONSchema + "/get_all_categories_response_schema.json")
		.withCustomReportMessage("data is retrieved successfully and JSON response schema is valid")
		.perform();
		
		Validations.verifyThat().response(filterByNameResponse)
		.extractedJsonValue("limit").isEqualTo(testData.getTestData("defaultLimit"))
		.withCustomReportMessage("Default limit is 10")
		.perform();
		
		Validations.verifyThat().response(filterByNameResponse)
		.extractedJsonValue("skip").isEqualTo(testData.getTestData("defaultSkip"))
		.withCustomReportMessage("Default skip is 0")
		.perform();
		
		Validations.verifyThat().response(filterByNameResponse)
		.extractedJsonValue("total").isEqualTo(testData.getTestData("emptyRecords"))
		.withCustomReportMessage("Total records should be 0")
		.perform();
		
	}
	
	@Test(description = "TC_04.01 Check the response when create new item")
	public void checkTheResponseWhenCreateNewCategory() {
		Response createNewItemResponse = categoriesApi.createNewCategoryItem(testData.getTestData("newID"), testData.getTestData("newName"));
		
		Validations.verifyThat().response(createNewItemResponse)
		.matchesSchema(testJSONSchema + "/post_create_new_item_response_schema.json")
		.withCustomReportMessage("data is retrieved successfully and JSON response schema is valid")
		.perform();
		
		Validations.verifyThat().response(createNewItemResponse).extractedJsonValue("name").isEqualTo(testData.getTestData("newName"))
		.withCustomReportMessage("Created ID's name is displayed in the response")
		.perform();
		
		// extra check that new created id is recorded successfully in DB
		String newCategoryId = RestActions.getResponseJSONValue(createNewItemResponse, "id");
		Response getNewItemResponse = categoriesApi.returnCategoryItemByID(newCategoryId);
		Validations.assertThat().response(createNewItemResponse).extractedJsonValue("id").isEqualTo(RestActions.getResponseJSONValue(getNewItemResponse, "id"))
		.withCustomReportMessage("new created id is recorded successfully in DB")
		.perform();
	}
	
	@Test(description = "TC_04.02 Check error handling  when create new item with exist id")
	public void checkTheResponseWhenCreateNewItemWithExistID() {
		
		Response createNewItemResponse = categoriesApi.createNewCategoryErrorHandling(testData.getTestData("existValidID"), testData.getTestData("newName"));
		Validations.verifyThat().response(createNewItemResponse)
		.extractedJsonValue("name").isEqualTo("BadRequest")
		.withCustomReportMessage("Error Name should be : BadRequest")
		.perform();
		
		Validations.verifyThat().response(createNewItemResponse)
		.extractedJsonValue("errors[0].message").isEqualTo("id must be unique")
		.withCustomReportMessage("Error message should be : id must be unique")
		.perform();	
	}

	
	@Test(description = "TC_05.01 Check the response when update an Item based on id")
	public void checkTheResponseWhenUpdateCategoryById() {
		Response updateCategoryByIdResponse = categoriesApi.updateCategoryByID(testData.getTestData("updateByID"),
				testData.getTestData("newName"));
		
		Validations.verifyThat().response(updateCategoryByIdResponse)
		.matchesSchema(testJSONSchema + "/patch_update_an_Item_based_on_id_schema.json")
		.withCustomReportMessage("data is retrieved successfully and JSON response schema is valid")
		.perform();
		
		Validations.verifyThat().response(updateCategoryByIdResponse).extractedJsonValue("id").isEqualTo(testData.getTestData("updateByID"))
		.withCustomReportMessage("Updated item's ID is displayed in the response")
		.perform();
		
		Validations.verifyThat().response(updateCategoryByIdResponse).extractedJsonValue("name").doesNotEqualIgnoringCaseSensitivity(testData.getTestData("newName"))
		.withCustomReportMessage("Updated item's name is changed/updated in the response")
		.perform();
		
		// extra check that category name is updated successfully in DB

		String updatedCategoryId = RestActions.getResponseJSONValue(updateCategoryByIdResponse, "id");
		Response getUpdatedItemResponse = categoriesApi.returnCategoryItemByID(updatedCategoryId);
				
		Validations.assertThat().response(updateCategoryByIdResponse).extractedJsonValue("name").isEqualTo(RestActions.getResponseJSONValue(getUpdatedItemResponse, "name"))
		.withCustomReportMessage("Category name is updated successfully in DB")
		.perform();
	}
	
	
	@Test(description = "TC_06.01 Check the response when delete an Item based on id")
	public void checkTheResponseWhenDeleteCategoryById() {
		
		Response deleteItemByIdResponse = categoriesApi.deleteCategoryById(testData.getTestData("validIDToBeDeleted"));
		
		Validations.verifyThat().response(deleteItemByIdResponse)
		.matchesSchema(testJSONSchema + "/delete_a_single_Item_based_on_id_schema.json")
		.withCustomReportMessage("data is retrieved successfully and JSON response schema is valid")
		.perform();
		
		Validations.verifyThat().response(deleteItemByIdResponse).extractedJsonValue("id").isEqualTo(testData.getTestData("validIDToBeDeleted"))
		.withCustomReportMessage("Deleted item's ID is displayed in the response")
		.perform();
		
		// Extra check that deleted item is not exist anymore in DB:
	Response getItemByIdResponse = categoriesApi.returnCategoryItemByIDErrorHandling(testData.getTestData("validIDToBeDeleted"));
		
		Validations.verifyThat().response(getItemByIdResponse)
		.extractedJsonValue("name").isEqualTo("NotFound")
		.withCustomReportMessage("Error Name should be : NotFound")
		.perform();
		
		Validations.verifyThat().response(getItemByIdResponse)
		.extractedJsonValue("message").isEqualTo("No record found for id '"+testData.getTestData("validIDToBeDeleted")+"'")
		.withCustomReportMessage("Error Message should be No record found for id")
		.perform();
	}
		
	@BeforeMethod()
	public void beforeMethod(Method m) {
		// Prepare category to be deleted
		if (m.getName().equals("checkTheResponseWhenDeleteCategoryById"))
		{
		categoriesApi.createNewCategoryItemToBeDeleted(testData.getTestData("validIDToBeDeleted"),
				testData.getTestData("newName"));
		}
	}
	
	@AfterMethod()
	public void afterMethod(Method m) {
		// if the update method passed , reset the name to its original value
		if (m.getName().equals("checkTheResponseWhenUpdateCategoryById"))
		{
		categoriesApi.resetTheUpdatedCategoryByID(testData.getTestData("updateByID"),
				testData.getTestData("newName"));
		}
	}	
	

}
