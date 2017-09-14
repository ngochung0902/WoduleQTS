package wodule.com.wodule.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.User;
import wodule.com.wodule.utils.Utility;

/**
 * Created by MyPC on 13/09/2017.
 */
public class FrmProfile2 extends Fragment {
    private ImageView ivNext,ivBack,ivCamera;
    private EditText edReligion,edUsername,edPassword,edCode;
    private TextView edStatus,edGender;
    private String userChoosenTask;
    private final int REQUEST_CAMERA = 0, CAPTURE_PICTURE = 1, SELECT_FILE = 2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_profile2, container, false);

        ivNext = (ImageView) view.findViewById(R.id.ivNext);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        edStatus = (TextView) view.findViewById(R.id.edStatus);
        edReligion = (EditText) view.findViewById(R.id.edReligion);
        edGender = (TextView) view.findViewById(R.id.edGender);
        edUsername = (EditText) view.findViewById(R.id.edUsername);
        edPassword = (EditText) view.findViewById(R.id.edPassword);
        edCode = (EditText) view.findViewById(R.id.edCode);
        ivCamera = (ImageView) view.findViewById(R.id.ivCamera);

        if (QTSHelp.getIsEdit(getActivity()))
            getProfile();

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid().equalsIgnoreCase("isOk")) {
                    setProfile();
                    FrmProfile3 fragment1 = new FrmProfile3();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit);
                    fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                    fragmentTransaction.commit();
                    QTSHelp.setIsEdit(getActivity(), false);
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrmProfile1 fragment1 = new FrmProfile1();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.anim_enter1, R.anim.anim_exit1);
                fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                fragmentTransaction.commit();
                QTSHelp.setIsEdit(getActivity(),true);

            }
        });

        edStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStatus();
            }
        });

        edGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectGender();
            }
        });

        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        return view;
    }

    private void selectStatus() {
        final CharSequence[] itemStatus = {"Single", "Married"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("SELECT STATUS");
        builder.setItems(itemStatus, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                edStatus.setText(itemStatus[item]);
            }
        });
        builder.show();
    }

    private void selectGender() {
        final CharSequence[] itemGender = {"Male", "Female"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("SELECT GENDER");
        builder.setItems(itemGender, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                edGender.setText(itemGender[item]);
            }
        });
        builder.show();
    }

    private void setProfile() {
        FrmProfile.newUser.setStatus(edStatus.getText().toString());
        FrmProfile.newUser.setReligion(edReligion.getText().toString());
        FrmProfile.newUser.setGender(edGender.getText().toString());
        FrmProfile.newUser.setUsername(edUsername.getText().toString());
        FrmProfile.newUser.setPassword(edPassword.getText().toString());
        FrmProfile.newUser.setCode(edCode.getText().toString());
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(FrmProfile.newUser);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }

    private void getProfile() {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("MyObject", "");
        User obj = gson.fromJson(json, User.class);
        edStatus.setText(String.valueOf(obj.getStatus()));
        edReligion.setText(String.valueOf(obj.getReligion()));
        edGender.setText(String.valueOf(obj.getGender()));
        edUsername.setText(String.valueOf(obj.getUsername()));
        edPassword.setText(String.valueOf(obj.getPassword()));
        edCode.setText(String.valueOf(obj.getCode()));
        if (FrmProfile.newUser.getPicture() != null) {
//            Glide.with(getActivity()).load("http://wodule.io/" + String.valueOf(FrmProfile.newUser.getPicture()))
//                    .asBitmap()
//                    .fitCenter()
//                    .signature(new StringSignature(UUID.randomUUID().toString()))
//                    .into(ivCamera);
//            ivCamera.setImageURI();
        }

        Log.e("testuser2",obj.getFirst_name()+obj.getMiddle_name());
        Log.e("testuser2",obj.getResidence_address()+obj.getVd1());
        Log.e("testuser2",obj.getStatus()+obj.getUsername());
    }

    private String checkValid() {
        if (edStatus.getText().toString().trim().length() == 0) {
            return getString(R.string.check_status);
        }
        if (edReligion.getText().toString().trim().length() == 0) {
            return getString(R.string.check_religion);
        }
        if (edGender.getText().toString().trim().length() == 0) {
            return getString(R.string.check_gender);
        }

            if (edUsername.getText().toString().trim().length() == 0) {
                return getString(R.string.check_username);
            }
            if (edPassword.getText().toString().trim().length() == 0) {
                return getString(R.string.check_password);
            }
//            if (QTSConstrains.bmAvatar == null) {
//                return getString(R.string.check_Picture);
//            }
            if (edCode.getText().toString().trim().length() == 0) {
                return getString(R.string.check_code);
            }

        return "isOk";
    }

    private void selectImage() {
        final CharSequence[] items = {"Capture photo", "Choose photo from Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Select a picture");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getActivity());

                if (items[item].equals("Capture photo")) {
                    userChoosenTask = "Capture photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose photo from Gallery")) {
                    userChoosenTask = "Choose photo from Gallery";
                    if (result)
                        galleryIntent();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Capture photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose photo from Gallery"))
                        galleryIntent();
                } else {
                    Toast.makeText(getActivity(), "Please grant camera permission for the app in Settings", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    public void cameraIntent() {
        Log.e("MainActivity", "Showing camera");
        try {
            if (Build.VERSION.SDK_INT < 30) {
                Uri imageUri = Uri.fromFile(getTempFile(getActivity()));
                Intent intent = createIntentForCamera(imageUri);
                startActivityForResult(intent, CAPTURE_PICTURE);
            } else {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                this.startActivityForResult(intent, REQUEST_CAMERA);
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please grant camera permission for the app in Settings", Toast.LENGTH_LONG).show();
            Log.e("MainActivity", "" + e.toString());
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.CAMERA},
                    2);
        }
    }

    private File getTempFile(Context context) {
        String fileName = "temp_hva_photo.jpg";
        File path = new File(Environment.getExternalStorageDirectory(),
                context.getPackageName());
        if (!path.exists()) {
            path.mkdir();
        }
        return new File(path, fileName);
    }

    private Intent createIntentForCamera(Uri imageUri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        return intent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                Uri uri = data.getData();
                setPhoto(3, uri);
                Log.e("MainActivity", "SELECT_FILE:" + data.getData().toString());
            } else if (requestCode == REQUEST_CAMERA) {
                Uri uri = data.getData();
                setPhoto(1, uri);
                Log.e("MainActivity", "REQUEST_CAMERA:" + data.getData().toString());
            } else if (requestCode == CAPTURE_PICTURE) {
                Uri uri = Uri.fromFile(getTempFile(getActivity()));
                setPhoto(2, uri);
                Log.e("MainActivity", "CAPTURE_PICTURE:" + uri.toString());
            }
        }
    }

    private void setPhoto(int is_scase, Uri imageUri) {
        if (QTSConstrains.bmAvatar != null) {
            QTSConstrains.bmAvatar.recycle();
            QTSConstrains.bmAvatar = null;
        }
        if (is_scase == 1) {
            try {
                QTSConstrains.bmAvatar = getThumbnail(imageUri);
                int rotation = QTSHelp.checkRotation(imageUri);
                if (rotation != 0) {
                    try {
                        QTSConstrains.bmAvatar = QTSHelp.scaleDown(QTSHelp.rotateImage(QTSConstrains.bmAvatar, rotation), true);
                    } catch (Exception e) {
                        QTSConstrains.bmAvatar = QTSHelp.scaleDown(QTSConstrains.bmAvatar, true);
                    }
                } else {
                    QTSConstrains.bmAvatar = QTSHelp.scaleDown(QTSConstrains.bmAvatar, true);
                }
                ivCamera.setImageBitmap(QTSConstrains.bmAvatar);
                ivCamera.setScaleType(ImageView.ScaleType.CENTER_CROP);
                QTSConstrains.pictureFile = getFiles(QTSConstrains.bmAvatar, "avaWodule");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else if (is_scase == 2) {
            try {
                QTSConstrains.bmAvatar = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                int rotation = QTSHelp.checkRotation(imageUri);
                if (rotation != 0) {
                    try {
                        QTSConstrains.bmAvatar = QTSHelp.scaleDown(QTSHelp.rotateImage(QTSConstrains.bmAvatar, rotation), true);
                    } catch (Exception e) {
                        QTSConstrains.bmAvatar = QTSHelp.scaleDown(QTSConstrains.bmAvatar, true);
                    }
                } else {
                    QTSConstrains.bmAvatar = QTSHelp.scaleDown(QTSConstrains.bmAvatar, true);
                }
                ivCamera.setImageBitmap(QTSConstrains.bmAvatar);
                ivCamera.setScaleType(ImageView.ScaleType.CENTER_CROP);
                QTSConstrains.pictureFile = getFiles(QTSConstrains.bmAvatar, "avaWodule");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (is_scase == 3) {
            try {
                QTSConstrains.bmAvatar = QTSHelp.scaleImage(getActivity(), imageUri);// MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), imageUri);
                int rotation = QTSHelp.checkRotation(imageUri);
                Log.e("PictureFragment", "rotation:" + rotation);
                if (rotation != 0) {
                    try {
                        QTSConstrains.bmAvatar = QTSHelp.rotateImage(QTSConstrains.bmAvatar, rotation);
                    } catch (Exception e) {
                        QTSConstrains.bmAvatar = QTSHelp.scaleDown(QTSConstrains.bmAvatar, true);
                    }
                } else {
                    QTSConstrains.bmAvatar = QTSHelp.scaleDown(QTSConstrains.bmAvatar, true);
                }
                ivCamera.setImageBitmap(QTSConstrains.bmAvatar);
                ivCamera.setScaleType(ImageView.ScaleType.CENTER_CROP);
                QTSConstrains.pictureFile = getFiles(QTSConstrains.bmAvatar, "avaWodule");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private File getFiles(Bitmap bitmap, String file_name) {
        File file = null;
        try {
            String path = Environment.getExternalStorageDirectory().toString();
            OutputStream fOut = null;
            file = new File(path, file_name + ".jpg");
            fOut = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            fOut.flush(); // Not really required
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Nullable
    private Bitmap getThumbnail(Uri imageUri) throws IOException {
        InputStream input = getActivity().getContentResolver().openInputStream(imageUri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > 1280) ? (originalSize / 1280) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = getActivity().getContentResolver().openInputStream(imageUri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) return 1;
        else return k;
    }
}
