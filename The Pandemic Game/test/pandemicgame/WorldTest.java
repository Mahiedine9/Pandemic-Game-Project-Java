package pandemicgame;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
class WorldTest {

	@Test
	public void testCreationIsOk() throws FileNotFoundException {
		World world = new World("carte1.json") ;
		assertEquals(World.class, world.getClass()) ;
		
			
	}


}
