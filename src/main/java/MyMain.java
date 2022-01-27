import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyMain
{

    public static void main(String[] args)
    {
        testCaseSearchHarryPotter();
    }


    static void testCaseSearchHarryPotter()
    {
        System.out.println("-----------");
        //System.setProperty("webdriver.chrome.driver","chromedriver");
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com");

        // find the search box element and type "Harry Potter and the Order of the Phoenix"
        driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys("Harry Potter and the Order of the Phoenix\n");

        // Check the text that shows how many results were found
        //   and split it into words
        String str = driver.findElement(By.cssSelector(" [data-component-type=\"s-result-info-bar\"]  [class=\"a-section a-spacing-small a-spacing-top-small\"] span")).getText();
        //System.out.println( str );

        String[] splitStr = str.split("\\s+");
        System.out.println(splitStr[2]);

        //click to choose filters:
        //  1) filter only books (click on "Books" in the left panel of the screen)
        //  2) filter only English language (click on "English" in the left panel of the screen)
        //System.out.println(driver.findElement(By.cssSelector("#departments ul li:nth-child(4)")).getText());
        driver.findElement(By.cssSelector("#departments ul li:nth-child(4) a")).click();
        driver.findElement(By.cssSelector("#p_n_feature_nine_browse-bin-title+ul div.a-checkbox")).click();


        // Find all elements of the books names
        //   and keep it in a list
        List<WebElement> myList = driver.findElements(By.cssSelector(".sg-col-inner a .a-size-medium"));

        // go over our list and find the longest name
        int maxLength = myList.get(0).getText().length();
        String maxLengthStr = myList.get(0).getText();
        int i = 0;
        while( i < myList.size())
        {
            int currNameLength = myList.get(i).getText().length();
            if(currNameLength > maxLength)
            {
                maxLength = currNameLength;
                maxLengthStr = myList.get(i).getText();
            }

            i = i + 1;
        }
        System.out.println("---------------");
        System.out.println("The longest name of book is:\n" + maxLengthStr);
        System.out.println("it has " + maxLength  + " letters");
        System.out.println("---------------");
    }

}///////////
