import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;

public class RentCatTest{
    // Reusable RentACat instance
    RentACat _rentACat;

    @Before
    public void setup(){
        _rentACat = new RentACat();
    }


    // Test an empty cat list
    @Test
    public void testListCatsNoCats(){
        ArrayList<Cat> catList = new ArrayList<Cat>();
        String list = _rentACat.listCats(catList);
        assertEquals("", list);
    }

    // Test a listing with valid cats
    @Test
    public void testListCatsValidCats(){
        ArrayList<Cat> catList = new ArrayList<Cat>();
        // Create some doubles for the Cat class
        Cat doubleCat1, doubleCat2;
        doubleCat1 = mock(Cat.class);
        doubleCat2 = mock(Cat.class);
        // Give them our desired toString values
        when(doubleCat1.toString()).thenReturn("Successful");
        when(doubleCat2.toString()).thenReturn("UnitTest");
        // Add the doubles to the catList
        System.out.println(doubleCat1.toString());
        catList.add(doubleCat1);
        catList.add(doubleCat2);
        // Run test
        String list = _rentACat.listCats(catList);
        assertEquals("Successful\nUnitTest\n", list);
    }

    // Test existance check on null cats
    @Test
    public void testCatExistsNull(){
        int id = 0;
        ArrayList<Cat> nullArray = null;
        boolean existResult = _rentACat.catExists(id, nullArray);
        assertEquals(false, existResult);
    }

    // Test existance check on a negative ID
    @Test
    public void testCatExistsNegativeID(){
        int id = -5;
        ArrayList<Cat> catArray = new ArrayList<Cat>();
        // Create some doubles for the Cat class
        Cat doubleCat1, doubleCat2;
        doubleCat1 = mock(Cat.class);
        doubleCat2 = mock(Cat.class);
        catArray.add(doubleCat1);
        catArray.add(doubleCat2);

        boolean existResult = _rentACat.catExists(id, catArray);
        assertEquals(false, existResult);
    }

    // Test existance check on valid cats in valid array
    @Test
    public void testCatExistsRealCats(){
        ArrayList<Cat> catArray = new ArrayList<Cat>();
        // Create some doubles for the Cat class
        Cat doubleCat1, doubleCat2;
        doubleCat1 = mock(Cat.class);
        doubleCat2 = mock(Cat.class);
        catArray.add(doubleCat1);
        catArray.add(doubleCat2);

        boolean cat1Exist = _rentACat.catExists(0, catArray);
        boolean cat2Exist = _rentACat.catExists(1, catArray);
        assertEquals(true, cat1Exist && cat2Exist);
    }

    
    //tests whether or not user can rent a cat that has not been rented
    @Test
    public void testRentRentedCat(){
        Cat c = mock(Cat.class);
        when(c.getRented()).thenReturn(true);
        assertEquals(_rentACat.rentCat(c), false);
    }    

    //tests whether or not user can rent a cat that has already been rented
    @Test 
    public void testRentUnrentedCat(){
        Cat c = mock(Cat.class);
        when(c.getRented()).thenReturn(false);
        assertEquals(_rentACat.rentCat(c), true);;
    }

    //tests whether or not user can return a cat that has been rented
    @Test 
    public void testReturnRentedCat(){
        Cat c = mock(Cat.class);
        when(c.getRented()).thenReturn(true);
        assertEquals(_rentACat.returnCat(c), true);
    }

    //tests whether or not user can return a cat that has not been rented
    @Test
    public void testReturnUnrentedCat(){
        Cat c = mock(Cat.class);
        when(c.getRented()).thenReturn(false);
        assertEquals(_rentACat.returnCat(c), false);
    }
}
