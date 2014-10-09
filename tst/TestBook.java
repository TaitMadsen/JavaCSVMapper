import java.util.Arrays;
import java.util.List;

public class TestBook {
    private String title;
    private String authorFirstName;
    private String authorLastName;

    public String getTitle() {
        return title;
    }

    @CSVMapperSetterMethod(fieldName = "Title")
    public void setTitle(String title) {
        this.title = title;
    }

   public String getAuthorName() {
       return authorFirstName + " " + authorLastName;
   }

   @CSVMapperSetterMethod(fieldName = "Author")
   public void setAuthorName(String name, String name2) {
       List<String> nameAsList = Arrays.asList(name.split(" "));
       this.authorFirstName = nameAsList.get(0);
       String lastName = "";
       for (int i = 1; i < nameAsList.size(); i++) {
           lastName += nameAsList.get(i) + " ";
       }
   }
}
