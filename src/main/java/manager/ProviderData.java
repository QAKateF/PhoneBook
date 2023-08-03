package manager;

import Models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProviderData {
    @DataProvider
    public Iterator<Object[]> userDTO() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("qwerty@gm.com").withPassword("abcD123$")
        });
        list.add(new Object[]{new User().withEmail("qwerty@gm.com").withPassword("abcD123$")
        });
        list.add(new Object[]{new User().withEmail("qwerty@gm.com").withPassword("abcD123$")
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userDto_CSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/reg_dataset.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[]{
                    new User().withEmail(split[0]).withPassword(split[1])});
        line = reader.readLine();
        }
        return list.iterator();
    }
}
