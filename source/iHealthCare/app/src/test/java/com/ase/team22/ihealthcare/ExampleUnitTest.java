package com.ase.team22.ihealthcare;

import android.util.Log;

import com.ase.team22.ihealthcare.helpers.BetterDoctorRESTClient;
import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONBetterDoctor;
import com.ase.team22.ihealthcare.jsonparsers.Deserializer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        //Log.i(this.getClass().getName(),responseJSONBetterDoctor.toString());
    }
}