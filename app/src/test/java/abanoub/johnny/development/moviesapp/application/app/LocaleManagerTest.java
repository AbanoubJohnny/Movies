package abanoub.johnny.development.moviesapp.application.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.test.ActivityTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.ParameterizedRobolectricTestRunner;

import java.util.Arrays;
import java.util.Collection;

import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(ParameterizedRobolectricTestRunner.class)
public class LocaleManagerTest extends ActivityTestCase {

    @Mock
    Context fakeContext;
    @Mock
    SharedPreferences sharedPrefs;
    @Mock
    SharedPreferences.Editor editor;
    PreferenceManager preferenceManager;
    // fields used together with @Parameter must be public
    public String lang;
    public String result;
    // creates the test data
    @ParameterizedRobolectricTestRunner.Parameters(name = "lang = {0},result = {1}")
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { Constants.ARABIC,Constants.ARABIC}, {Constants.ENGLISH,Constants.ARABIC }, {Constants.ENGLISH,Constants.ARABIC},{Constants.ARABIC,Constants.ENGLISH} };
        return Arrays.asList(data);
    }

    public LocaleManagerTest(String lang, String result){
        this.lang = lang;
        this.result = result;
    }

   @Before
   public void setUp() throws Exception{
       MockitoAnnotations.initMocks(this);
       this.sharedPrefs = mock(SharedPreferences.class);
       this.fakeContext = mock(Context.class);
       this.editor = mock(SharedPreferences.Editor.class);
       preferenceManager= mock(PreferenceManager.class);
       when(preferenceManager.getDefaultSharedPreferences(fakeContext)).thenReturn(sharedPrefs);
       when(sharedPrefs.getString(anyString(),isNull(String.class))).thenReturn(null);
       when(sharedPrefs.edit()).thenReturn(editor);
       when(editor.putString(anyString(),anyString())).thenReturn(editor);
       when(editor.commit()).thenReturn(true);
    }
    @Test
    public void languageShouldBeTrue(){
        LocaleManager.setNewLocale(fakeContext,lang);
        assertThat(LocaleManager.getLanguage(fakeContext), is(result));
    }




}