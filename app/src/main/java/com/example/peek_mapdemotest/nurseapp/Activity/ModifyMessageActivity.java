package com.example.peek_mapdemotest.nurseapp.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peek_mapdemotest.nurseapp.Operation.Base64Tool;
import com.example.peek_mapdemotest.nurseapp.Operation.UserOperation;
import com.example.peek_mapdemotest.nurseapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ModifyMessageActivity extends AppCompatActivity {
    private TextView name;
    private TextView password;
    private Button ensure;
    private ImageView HeadButton;

    private static final int PHOTO_REQUEST_CAREMA = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private File tempFile;

    private String baseString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_message);
        name = (EditText)findViewById(R.id.editText_name);
        password = (EditText)findViewById(R.id.editText_password);
        name.setText(UserOperation.user.getName());
        HeadButton = (ImageView) findViewById(R.id.imageButton_head);

        ensure = (Button)findViewById(R.id.ensure) ;


        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getApplicationContext().getFilesDir().getAbsolutePath() + "/pic.jpg");
                try {
                    if (file.isFile() && file.exists()) { //判断文件是否存在

                        InputStreamReader read = new InputStreamReader(new FileInputStream(file));//考虑到编码格式
                        BufferedReader bufferedReader = new BufferedReader(read);

                        String tempString = null;
                        while ((tempString = bufferedReader.readLine()) != null) {
                            baseString = baseString + tempString;
                        }
                        read.close();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String nameStr = name.getText().toString();
                String pass = password.getText().toString();
                try {
                    Toast.makeText(ModifyMessageActivity.this,UserOperation.user.getName() ,Toast.LENGTH_SHORT).show();
                    ArrayList resp = UserOperation.modifyUser(pass,nameStr,"avatar");
                    if(Integer.parseInt((String) resp.get(0))==200){
                        Toast.makeText(ModifyMessageActivity.this,UserOperation.user.getName() ,Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        String data = (String) resp.get(1);
                        JSONObject object = new JSONObject(data);
                        String respJsonObject = object.getString("message");
                        Toast.makeText(ModifyMessageActivity.this,respJsonObject ,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(ModifyMessageActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        HeadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeHeadIcon();
            }
            private void changeHeadIcon() {
                final CharSequence[] items = {"相册", "拍照"};
                AlertDialog dlg = new AlertDialog.Builder(ModifyMessageActivity.this).setTitle("选择图片").setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // 这里item是根据选择的方式，
                        if (item == 0) {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent,
                                    PHOTO_REQUEST_GALLERY);
                        } else {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                tempFile = getCreateFile();
                                Uri uri;
                                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){
                                    uri = Uri.fromFile(tempFile);
                                }else{
                                    /**
                                     * 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
                                     * 并且这样可以解决MIUI系统上拍照返回size为0的情况
                                     */
                                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    uri = FileProvider.getUriForFile(getApplicationContext(), "com.example.peek_mapdemotest.nurseapp.provider", tempFile);
                                }
                                Log.e("nanchen",uri.toString());
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
                            } else {
                                Toast.makeText(ModifyMessageActivity.this, "未找到存储卡，无法存储照片！",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }).create();
                dlg.show();

            }
        });
    }
    private  File getCreateFile() {

        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = ModifyMessageActivity.this.getExternalCacheDir().getPath();
        } else {
            cachePath = ModifyMessageActivity.this.getCacheDir().getPath();
        }
        File dir = new File(cachePath);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStamp = format.format(new Date());
        String fileName = "robot_" + timeStamp + ".png";
        File file = new File(dir, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String mCurrentPhotoPath = file.getAbsolutePath();
        return file;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                crop(uri);

            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                crop(Uri.fromFile(tempFile));


            } else {
                Toast.makeText(ModifyMessageActivity.this, "未找到存储卡，无法存储照片！",
                        Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            if (data != null) {
//                Bundle extras = data.getExtras();
                final Bitmap bitmap = data.getParcelableExtra("data");
//                final Bitmap bitmap = extras.getParcelable("data");
                HeadButton.setImageBitmap(bitmap);

                // 保存图片到internal storage
                FileOutputStream outputStream;

                try {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();
                    // out.close();
                    // final byte[] buffer = out.toByteArray();
                    // outputStream.write(buffer);
                    outputStream = ModifyMessageActivity.this.openFileOutput("_head_icon.jpg", Context.MODE_PRIVATE);
                    out.writeTo(outputStream);
                    out.close();
                    outputStream.close();


                    String base64 = "data:image/jpeg;base64," + Base64Tool.imageToBase(getApplicationContext().getFilesDir().getAbsolutePath() + "/_head_icon.jpg");
//                    System.out.println(base64);

                    File file = new File(getApplicationContext().getFilesDir().getAbsolutePath() + "/base64.txt");
                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(base64);
                    bw.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                if (tempFile != null && tempFile.exists())
                    tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}
