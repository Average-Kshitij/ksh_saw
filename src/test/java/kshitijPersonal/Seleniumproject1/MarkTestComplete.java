package kshitijPersonal.Seleniumproject1;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class MarkTestComplete extends Initialise{

	String URL = "http://mydy.dypatil.edu/";
	String user ="Ameya Sawant";
	
	@FindBy(xpath="//div[@class ='course-content']//ul //li //span[@class='instancename']")
	public List<WebElement> selects;

	
	@Test
	public void medthod() throws InterruptedException
	{
		setup();
		getURL(URL);
		WebDriverWait wait =  new WebDriverWait(getDriver(), 5);
		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@name='username']"))));
		getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("ame.saw.ht21@dypatil.edu");
		
		WebElement nextButton= getDriver().findElement(By.xpath("//input[@type='submit']"));
		nextButton.click();
		
		WebElement password= getDriver().findElement(By.xpath("//input[@type='password']"));
		wait.until(ExpectedConditions.visibilityOf(password));
		password.sendKeys("dypatil@123");
		
		WebElement loginButton= getDriver().findElement(By.xpath("//input[@type='submit'and @id='loginbtn']"));
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		loginButton.click();
		
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOfAllElements(getDriver().findElements(By.xpath("//table[@id='tbl-subject'] //tr //td //a[text()='Launch']"))));
		
		boolean isCurrentPageDone = false;
		
		
		while(!(getDriver().findElements(By.xpath("//div[@id='tbl-subject_paginate']/ul/li[@class='next disabled']")).size()>0))
		{
		
			
			
			if(isCurrentPageDone)
			{
				WebElement nextPageButton =getDriver().findElement(By.xpath("//div[@id='tbl-subject_paginate']/ul/li[@class='next']/a"));
				nextPageButton.click();
				Thread.sleep(1000);
				isCurrentPageDone = false;
			}
		
			List<WebElement> subjects = getDriver().findElements(By.xpath("//table[@id='tbl-subject'] //tr //td //a[text()='Launch']"));
		
		for(WebElement x : subjects)
		{
			
			String parent=getDriver().getWindowHandle();
			
			x.click();
			
			int windowsPresentCount = getDriver().getWindowHandles().size();
			
			Iterator<String> I1= getDriver().getWindowHandles().iterator();
			
			
			while(I1.hasNext())
			{

			String child_window=I1.next();


			if(!parent.equals(child_window))
			{
				getDriver().switchTo().window(child_window);

			System.out.println(getDriver().switchTo().window(child_window).getTitle());
			
			
			WebElement completionPercent = getDriver().findElement(By.xpath("//div[@id='inst17821'] //div/a //span"));
			
			String completionPercentText = completionPercent.getText();
			

			//Gets the list of all the content that needs to be completed in the subject.
			 
			List<WebElement> allContentInSubject = getDriver().findElements(By.xpath("//div[@class ='course-content']//ul //li //span[@class='instancename']"));
			
			int allContentInSubjectSize = allContentInSubject.size();
			
					if (!completionPercentText.contains("100")) {
						if (allContentInSubjectSize > 0) {
							for (int i=0 ; i<allContentInSubjectSize ; i++) {
								List<WebElement> allContentInSubjectReInit = getDriver().findElements(By.xpath("//div[@class ='course-content']//ul //li //span[@class='instancename']"));
								
								
								
								getDriver().switchTo().window(child_window);
								String contentName = allContentInSubjectReInit.get(i).getText();

								if (contentName.contains("Discussion") || contentName.contains("discussion") ) {
								perFormDiscussionForumContent(allContentInSubjectReInit.get(i));
								}
								else if(contentName.contains("Assignment"))
								{
								//assignmentContent(allContentInSubjectReInit.get(i));
								}
								else if(contentName.contains("Quiz"))
								{
								//quizContent(allContentInSubjectReInit.get(i));
								}
								else
								{
								//onlyClickContent(allContentInSubjectReInit.get(i));
								}
							}

						}
					}
			
			getDriver().close();
			
			
			
			}
			
			
			}
			getDriver().switchTo().window(parent);
			
			
			
		}
		
		isCurrentPageDone = true;
		
	}
		
}
	
	
	private void perFormDiscussionForumContent(WebElement element) throws InterruptedException 
	{
		WebDriverWait wait =  new WebDriverWait(getDriver(), 5);
		boolean studentFound = false;
		
		element.click();
		
		Thread.sleep(2000);
		
		List<WebElement> studentNames = getDriver().findElements(By.xpath("//table //tbody/tr/td[3]/a"));
		
		for(WebElement x3 : studentNames)
		{
			
		String currStudentName= x3.getText();
		System.out.println(currStudentName);
		if(currStudentName.equalsIgnoreCase("Ameya Sawant"))
		{
			studentFound = true;
			break;
		}
		
		}
			
		if(!studentFound)
		{
		
		WebElement addANewDiscussionButton =getDriver().findElement(By.xpath("//input[@type='submit' and @value='Add a new discussion topic']"));
		wait.until(ExpectedConditions.visibilityOf(addANewDiscussionButton));
		addANewDiscussionButton.click();
		
		
		WebElement addSubjectTextBox =getDriver().findElement(By.xpath("//input[@name='subject']"));
		addSubjectTextBox.sendKeys(".");
		
		
		WebElement addMessageBox =getDriver().findElement(By.xpath("//div[@id='id_messageeditable']"));
		addMessageBox.sendKeys(".");
		
		WebElement submitToForumButton =getDriver().findElement(By.xpath("//input[@name='submitbutton']"));
		submitToForumButton.click();
		
		}
		Thread.sleep(4000);
		
		WebElement goBackToContentPageFromAssignmentPage = getDriver().findElement(By.xpath("//div[@id='page-navbar']/child::div //ul/child::li[2]/a"));
		goBackToContentPageFromAssignmentPage.click();
		
	}
	

	private void onlyClickContent(WebElement element) throws InterruptedException
	{
		element.click();
		Thread.sleep(2000);
		getDriver().navigate().back();
	}
	
	
	private void assignmentContent(WebElement element) throws InterruptedException
		{
		element.click();
		
		WebDriverWait wait =  new WebDriverWait(getDriver(), 5);
		
		Thread.sleep(2000);
		
		int addSubmissionCount = getDriver().findElements(By.xpath("//*[@type='submit' and @value='Add submission']")).size();
		
		if (addSubmissionCount > 0) {
			WebElement addSumissionButton = getDriver()
					.findElement(By.xpath("//*[@type='submit' and @value='Add submission']"));
			addSumissionButton.click();

			int uploadAssignmentBoxCount = getDriver().findElements(By.xpath("//label[@for='id_files_filemanager']"))
					.size();
			int inputAssignmentBoxCount = getDriver().findElements(By.id("id_onlinetext_editoreditable")).size();

			if (uploadAssignmentBoxCount > 0) {
				
				System.out.println("---Performing uploadAssignmentBoxCount---- ");

				wait.until(ExpectedConditions
						.visibilityOf(getDriver().findElement(By.xpath("//a[@role='button' and @title='Add...']"))));

				WebElement uploadFileBox = getDriver().findElement(By.xpath("//a[@role='button' and @title='Add...']"));
				uploadFileBox.click();
				
				//System.out.println("==========DDDDDDDDDDD=========================");
				
				Thread.sleep(2000);
				
				wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@name='repo_upload_file' and @type='file']"))));
				
				//System.out.println("==========FFFFFFFFFFFFFFFFF=========================");

				WebElement chooseFileButton = getDriver()
						.findElement(By.xpath("//input[@name='repo_upload_file' and @type='file']"));
				chooseFileButton.sendKeys("C:\\Users\\MY PC\\Desktop\\ASSIGNMENT.docx");

				//System.out.println("==========KKKKKKKKKKKKKKKKKKK=========================");
				
				WebElement uploadButton = getDriver()
						.findElement(By.xpath("//button[@class='fp-upload-btn btn-primary btn']"));
				uploadButton.click();

				WebElement saveChangesButton = getDriver().findElement(By.xpath("//input[@id='id_submitbutton']"));
				saveChangesButton.click();
				
				WebElement goBackToContentPageFromAssignmentPage = getDriver().findElement(By.xpath("//div[@id='page-navbar']/child::div //ul/child::li[2]/a"));
				goBackToContentPageFromAssignmentPage.click();
				

			} else if (inputAssignmentBoxCount > 0) {
				
				System.out.println("---Performing inputAssignmentBoxCount---- ");
				
				WebElement insertAssigmentBox = getDriver().findElement(By.id("id_onlinetext_editoreditable"));
				insertAssigmentBox.sendKeys(".");

				WebElement saveChangesButton = getDriver().findElement(By.xpath("//input[@id='id_submitbutton']"));
				saveChangesButton.click();
				
				WebElement goBackToContentPageFromAssignmentPage = getDriver().findElement(By.xpath("//div[@id='page-navbar']/child::div //ul/child::li[2]/a"));
				goBackToContentPageFromAssignmentPage.click();

			}
		}
		else
		{
			getDriver().navigate().back();
		}

	}
	
	
	private void quizContent(WebElement element) throws InterruptedException
	{
		
		element.click();
		
		int attemQuizNowButtonCount = getDriver().findElements(By.xpath("//input[@type='submit' and @value='Attempt quiz now']")).size();
		
		if(attemQuizNowButtonCount>0)
		{
		WebElement saveChangesButton = getDriver().findElement(By.xpath("//input[@type='submit' and @value='Attempt quiz now']"));
		saveChangesButton.click();
		
		Thread.sleep(1000);
		
		while(getDriver().findElements(By.xpath("//input[@type='submit' and @value='Submit all and finish']")).size() == 0)
		{
			
			Random rand = new Random();
			int optionNum =  rand.nextInt(3);
			
			String mcqOptionNumberXpath = "//input[@type='radio' and @value='"+optionNum+"']";
			
			WebElement mcqOptionNumber = getDriver().findElement(By.xpath(mcqOptionNumberXpath));
			mcqOptionNumber.click();
			
			WebElement nextButton = getDriver().findElement(By.xpath("//input[@type='submit' and @value='Next']"));
			nextButton.click();
			
			Thread.sleep(1000);
			
		}
		
		WebElement submitAll = getDriver().findElement(By.xpath("//input[@type='submit' and @value='Submit all and finish']"));
		submitAll.click();
		
		Thread.sleep(1000);
		
		WebElement submitAllDialogButton = getDriver().findElement(By.xpath("//input[@type='button' and @value='Submit all and finish']"));
		submitAllDialogButton.click();
		
		
		Thread.sleep(2000);
		
		WebElement finishReview = getDriver().findElement(By.xpath("//div[@class='submitbtns'] //a[text()='Finish review']"));
		finishReview.click();
		
		Thread.sleep(1000);
		
		WebElement goBackToContentPageFromAssignmentPage = getDriver().findElement(By.xpath("//div[@id='page-navbar']/child::div //ul/child::li[2]/a"));
		goBackToContentPageFromAssignmentPage.click();
		
		}
		else
		{
			getDriver().navigate().back();
		}
	}
	
		
}
