package wodule.com.wodule.helper;

import android.graphics.Bitmap;

import java.io.File;
import java.util.ArrayList;

import wodule.com.wodule.object.ExamCategory;
import wodule.com.wodule.object.UserObject;

/**
 * Created by MyPC on 24/07/2017.
 */
public class QTSConstrains
{
    public static String ID = "";
    public static String IDCategory = "";
    public static UserObject userObj = new UserObject();
    public static boolean checkdate = false;
//    public static String code = "";
    public static String SHAREPRE_ID = "hungQTS";
    public static File audiofile1 = null;
    public static File audiofile2 = null;
    public static File audiofile3 = null;
    public static File audiofile4 = null;

    public static Bitmap bmAvatar = null;
    public static File pictureFile = null;
    public static String FONT_SANSPRO_LIGHT = "fonts/ProximaNovaBold.otf";
    public static ArrayList<ExamCategory> arrayList = new ArrayList<ExamCategory>();
    public static ArrayList<UserObject> arrUserObject = new ArrayList<UserObject>();
}
