package com.academyatinfo.multtable.certification;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.tools.Constants;
import com.academyatinfo.multtable.tools.Painter;
import com.academyatinfo.multtable.ui.activitys.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CertificationActivity extends BaseActivity {

    @BindView(R.id.image_certificate)
    ImageView certificate;

    private Bitmap bitmap;
    private File pictureFile;
    private final String path = "images/certificate.png";
    private Intent intent;
    private String userName, familyName, degree, gender, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_certification);

        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        intent = getIntent();

        userName = intent.getStringExtra(Constants.KEY_NAME);
        familyName = intent.getStringExtra(Constants.KEY_FAMILY_NAME);
        degree = intent.getStringExtra(Constants.KEY_DEGREE);
        gender = intent.getStringExtra(Constants.KEY_GENDER);
        date = intent.getStringExtra(Constants.KEY_DATE);

        try {
            bitmap = BitmapFactory.decodeStream(getAssets().open(path));
            bitmap = Painter.paint(this, bitmap, userName, familyName, degree, gender, date);
            storeImage(bitmap);
            certificate.setImageBitmap(bitmap);
        } catch (IOException e) {

        }
    }

    private void storeImage(Bitmap image) {
        pictureFile = getOutputMediaFile();
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                final Uri contentUri = Uri.fromFile(pictureFile);
                scanIntent.setData(contentUri);
                sendBroadcast(scanIntent);
            } else {
                final Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()));
                sendBroadcast(intent);
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }


    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + "/جداء/");

        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdir();
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        String mImageName = "certificate_" + timeStamp + ".jpg";
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    public void share_certification(View view) {
        Intent shareIntent = new Intent();
        Uri uri = Uri.fromFile(pictureFile);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "مشاركة الشهادة"));

    }

    public void click_back(View view) {
        finish();
    }
}
