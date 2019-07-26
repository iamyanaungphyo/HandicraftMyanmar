package com.team02.handicraftmyanmar;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team02.handicraftmyanmar.controller.accountController;
import com.team02.handicraftmyanmar.model.FirebaseDB;
import com.team02.handicraftmyanmar.model.accountModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;

    @BindView(R.id.etRegisterName)
    EditText etRegisterName;

    @BindView(R.id.etRegisterPhone)
    EditText etRegisterPhone;

    @BindView(R.id.etRegisterPassword)
    EditText etRegisterPassword;

    @BindView(R.id.etRegisterConfirmPassword)
    EditText etRegisterConfirmPassword;



    @BindView(R.id.btnSignup)
    Button btnSignup;


    Spinner regionSpinner,citySpinner;
    String[] spinner = null;
    private accountController accountController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this, this);

        regionSpinner=findViewById(R.id.register_region_spinner);
        citySpinner=findViewById(R.id.register_city_spinner);
        accountController = new accountController();
        regionSpinner.setOnItemSelectedListener(this);


    }

    @OnClick(R.id.btnSignup)
    public void SavetoCloud() {

        if(etRegisterName.getText().toString().equals("")){
            etRegisterName.setError("Required  Username.");
            return;
        }else if(etRegisterPhone.getText().toString().equals("")){
            etRegisterPhone.setError("Required Phone Number.");
            return;
        }else if(etRegisterPassword.getText().toString().equals("")){
            etRegisterPassword.setError("Required Password.");
            return;
        }else if(etRegisterConfirmPassword.getText().toString().equals("")){
            etRegisterConfirmPassword.setError("Required Confirm Password.");
            return;
        }


        String message = "";

            boolean isSave = accountController.save(new accountModel.Account(
                    etRegisterName.getText().toString(),
                    etRegisterPhone.getText().toString(),
                    etRegisterPassword.getText().toString(),
                    regionSpinner.getSelectedItem().toString(),
                    citySpinner.getSelectedItem().toString()

            ));

            if (isSave) {
                if(!etRegisterPassword.getText().toString().equals(etRegisterConfirmPassword.getText().toString())){
                    message = "retype password";
                    ClearPassword();

                }else {
                    ClearData();
                    message = "Sign Up Successful!";


                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                Snackbar.make(findViewById(R.id.relativeLayout), message, Snackbar.LENGTH_LONG).show();
            }





    }

    private void ClearData(){
        etRegisterName.setText("");
        etRegisterPhone.setText("");
        etRegisterPassword.setText("");
        etRegisterConfirmPassword.setText("");
    }

    private void ClearPassword(){
        etRegisterPassword.setText("");
        etRegisterConfirmPassword.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("bugg","enter"+position);
        switch (position) {

            case 0:
                spinner = new String[]{"ကမာရွတ်", "ကိုကိုးကျွန်း", "ကော့မှူး", "ကျောက်တန်း", "ကျောက်တံတား", "ကြည့်မြင်တိုင် ", "ကွမ်းခြံကုန်း", "ခရမ်း", "စမ်းချောင်း", "ဆိပ်ကမ်း", "ဆိပ်ကြီးခနောက်တို ", "တာမွေ", "တိုက်ကြီး ", "တောင်ဥက္ကလာပ", "တွံတေး", "ထန်းတပင်", "ဒဂုံ", "ဒလ", "ဒေါပုံ ", " ပုဇွန်တောင် ", "ဗဟိန်း", "ဗိုလ်တထောင်", "မင်္ဂလာတောင်ညွန့်", "မဂ်လာဒုံ ", "မရမ်းကုန်း", "မြောက်ဥက္ကလာပ", "မှော်ဘီ", "ရန်ကင်း", "ရွှေပြည်သာ", "လမ်းမတော်", "လသာ", "လှည်းကူး", "လှိုင်", "လှိုင်သာယာ", "သဃန်းကျွန်း", "သာကေတ", "သုံးခွ", "အင်းစိန် ", "အလုံ"};
                break;
            case 1:
                spinner = new String[]{"ကျောက်ဆည်", "ကျောက်ပန်းတောင်း", "ချမ်းမြသာစည်", "ချမ်းအေးသာစံ", "ငါန်းဇွန်", "စဉ့်ကိုင်", "စဉ့်ကူး", "ညောင်ဦး ", "တောင်သာ", "တံတားဦး", "နွားထိုးကြီး", "ပုသိမ်ကြီး", "ပျော်ဘွယ်", "ပြင်ဦးလွင်", "ပြည်ကြီးတံခွန်", "မတ္တရာ", "မလှိုင် ", "မဟာအောင်မြေ", "မိတ္ထီလာ", "မိုးကုတ်", "မြင်းခြံ", "မြစ်သား", "ရမည်းသင်း", "ဝမ်းတွင်း", "သပိတ်ကျဉ်း", "သာစည်", "အမရပူရ", "အောင်မြေသာစံ"};
                break;
            case 2:
                spinner = new String[]{"ခေါင်လန်ဖူး", "ချီဗွေ", "ဆော့လော်", "ဆွမ်ပရာဘွမ်", "တနိုင်း", "နောင်မွန်း", "ပူတာအို", "ဖားကန့်", "ဗန်းမော်", "မချမ်းဘော", "မန်စီ", "မိုးကောင်း", "မိုးညှင်း", "မိုးမောက်", "မြစ်ကြီးနား", "ရွှေကူ", "ဝိုင်းမော်", "အင်ဂျန်းယန်"};
                break;
            case 3:
                spinner = new String[]{"ဒီမောဆို", "ဖရူဆို", "ဖားဆောင်း", "ဘောလခဲ", "မယ်စဲ့", "ရှားတော", "လွိုင်ကော်"};
                break;
            case 4:
                spinner = new String[]{"ကော့ကရိတ်", "ကြာအင်းဆိပ်ကြီး", "ဖာပွန်", "ဘားအံ", "မြဝတီ", "လှိုင်းဘွဲ", "သံတောင်ကြီး"};
                break;
            case 5:
                spinner = new String[]{"ကန်ပက်လက်", "တီးတိန်", "တွန်းဇံ", "ထန်တလန်", "ပလက်ဝ", "ဖလန်း", "မင်းတပ်", "မတူပီ", "ဟားခါး"};
                break;
            case 6:
                spinner = new String[]{"ကန်နီ", "ကန့်ဘလူ", "ကလေး", "ကလေးဝ", "ကသာ", "ကောလင်း", "ကျွန်းလှ", "ခင်ဦး", "ခန္တီး", "ချောင်းဦး", "စစ်ကိုင်း", "ဆားလင်းကြီး", "တန့်ဆည်", "တမူး", "ထီးချိုင့်", "ဒီပဲယင်း", "နန်းယွန်း", "ပင်လည်ဘူး", "ပုလဲ", "ဖောင်းပြင်", "ဗန်းမောက်", "ဘုတလင်", "မင်းကင်း", "မုံရွာ", "မော်လိုက်", "မြင်းမူ", "မြောင်", "ယင်းမာပင်", "ရေဦး", "ရွှေဘို", "လဟယ်", "လေရှိုး", "ဝက်လက်", "ဝန်းသို", "ဟုမ္မလင်း", "အင်းတော်", "အရာတော်"};
                break;
            case 7:
                spinner = new String[]{"ကော့သောင်း", "ကျွန်းစု", "တနင်္သာရီ", "ထားဝယ်", " ပုလော", "ဘုတ်ပြင်း", "မြိတ်", "ရေဖြူ", "လောင်းလုံ", "သရက်ချောင်း"};
                break;
            case 8:
                spinner = new String[]{"ကဝ", "ကျောက်ကြီး", "ကျောက်တံခါး", "ညောင်လေးပင်", "တောင်ငူ", "ထန်းတပင်", "ဒိုက်ဦး", "ပဲခူး", "ဖြူး", "ရေတာရှည်", "ရွှေကျင်", "ဝေါ", "သနပ်ပင်", "အုတ်တွင်း"};
                break;
            case 9:
                spinner = new String[]{"ကြို့ပင်ကောက်", "ဇီးကုန်း", "နတ်တလင်း", "ပန်းတောင်း", "ပေါက်ခေါင်း", "ပေါင်းတည်", "ပြည်", "မင်းလှ", "မိုးညို", "ရွှေတောင်", "လက်ပံတန်း", "သာယာဝတီ", "သဲကုန်း", "အုတ်ဖို"};
                break;
            case 10:
                spinner = new String[]{"ကံမ", "ချောက်", "ဂန့်ဂေါ", "ငဖဲ", "စလင်း", "စေတုတ္တရာ", "ဆင်ပေါင်ဝဲ", "ဆိပ်ဖြူ", "ဆော", "တောင်တွင်းကြီး", "ထီးလင်း", "နတ်မောက်", "ပခုက္ကူ", "ပေါက်", "ပွင့်ဖြူ", "မကွေး", "မင်းတုန်း", "မင်းဘူး", "မင်းလှ", "မြိုင်", "မြို့သစ်", "ရေစကြို့", "ရေနံချောင်း", "သရက်", "အောင်လံ"};
                break;
            case 11:
                spinner = new String[]{"ကျိုက်ထို", "ကျိုက်မရော", "ချောင်းဆုံ", "ပေါင်", "ဘီးလင်း", "မုဒုံ", "မော်လမြိုင်", "ရေး", "သထုံ", "သံဖြူဇရပ်"};
                break;
            case 12:
                spinner = new String[]{"ကျော်တော်", "ကျော်ဖြူ", "ဂွ", "ပုဏ္ဏားကျွန်း", "စစ်တွေ", "တောင်ကုတ်", "ပေါက်တော", "ဘူးသီးတောင်", "မင်းပြား", "မာန်အောင်", "မောင်တော", "မြေပုံ", "မြောက်ဦး", "ရမ်းဗြဲ", "ရသေ့တောင်", "သံတွဲ", "အမ်း"};
                break;
            case 13:
                spinner = new String[]{"ကလော", "ကျေးသီး", "ကွန်ဟိန်း", "ဆီဆိုင်", "ညောင်ရွှေ", "တောင်ကြီး", "နမ့်စန်", "ပင်လောင်း", "ပင်းတယ", "ဖယ်ခုံ", "မိုင်းကိုင်", "မိုင်းပန်", "မိုင်းရှုး", "မိုးနဲ", "မောက်မယ်", "ရပ်စောက်", "ရွာငံ", "လင်းခေး", "လဲချား", "လွိုင်လင်", "ဟိုပုံး"};
                break;
            case 14:
                spinner = new String[]{"ကုန်းကြမ်း", "ကျောက်မဲ", "ကွတ်ခိုင်", "ကွမ်းလုံ", "တန့်ယန်း", "နမ္မတူ", "နမ့်ခမ်း", "နမ့်ဆန်", "နားဖန့်", "နောင်ချို", "ပန်ဆန်း", "ပန်ဝိုင်း", "မက်မန်း", "မန်တုန်", "မဘိမ်း", "မိုင်းမော", "မိုင်းရယ်", "မိုးမိတ်", "မူဆယ်", "လားရှိူး", "လောက်ကိုင်", "သိန္နီ", "သီပေါ", "ဟိုပန်"};
                break;
            case 15:
                spinner = new String[]{"ကျိုင်းတုံ", "တာချီလိတ်", "မိုင်းခတ်", "မိုင်းဆတ်", "မိုင်းတုံ", "မိုင်းပျင်း", "မိုင်းဖြတ်", "မိုင်းယန်း", "မိုင်းယောင်း", "မိုင်းလား"};
                break;
            case 16:
                spinner = new String[]{"ကန်ကြီးထောင့်", "ကျိုက်လတ်", "ကျုံပျော်", "ကျောင်းကုန်း", "ကြံခင်း", "ငပုတော", "ဇလွန်", "ညောင်တုန်း", "ဒေးဒရဲ", "ဓနုဖြူ", "ပန်းတနော်", "ပုသိမ်", "ဖျာပုံ", "ဘိုကလေး", "မအူပင်", "မော်လမြိုင်ကျွန်း", "မြန်အောင်", "ရေကြည်", "မြောင်းမြ", "လပွတ္တာ", "လေးမျက်နှာ", "ဝါးခယ်မ", "သာပေါင်း", "ဟင်္သာတ", "အင်္ဂပူ", "အိမ်မဲ"};
                break;
            case 17:
                spinner = new String[]{"ဇေယျာသီရိ", "တပ်ကုန်း", "ပျဉ်းမနား", "လယ်ဝေး", "ဥတ္တရသီရိ", "ဇမ္ဗူသီရိ", "ဒက္ခိဏသီရိ", "ပုဗ္ဗသီရိ"};
                break;
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, spinner);
        citySpinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
