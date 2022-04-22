package com.ug.air.alrite.Utils.Calculations;

import com.ug.air.alrite.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Instructions {

    public List GetInstructions(float ag, String weight, String s){

        List<Integer> messages = new ArrayList<>();

        if (!weight.isEmpty()){
            float we = Float.parseFloat(weight);
            if (we >= 4.0 && we < 6.0){
                if (s.contains("Convulsions")){
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin2, R.string.gentamicin2,
                            R.string.convulsions, R.string.diazepam2, R.string.convulsions1,
                            R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                            R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }else{
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin2, R.string.gentamicin2,
                            R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }
            }else if (we >= 6.0 && we < 10.0){
                if (s.contains("Convulsions")){
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin4, R.string.gentamicin4,
                            R.string.convulsions, R.string.diazepam4, R.string.convulsions1,
                            R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                            R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }else{
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin4, R.string.gentamicin4,
                            R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }
            }else if (we >= 10.0 && we < 14.0){
                if (s.contains("Convulsions")){
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin12, R.string.gentamicin12,
                            R.string.convulsions, R.string.diazepam12, R.string.convulsions1,
                            R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                            R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }else{
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin12, R.string.gentamicin12,
                            R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }

            }else if (we >= 14.0){
                if (s.contains("Convulsions")){
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin3, R.string.gentamicin3,
                            R.string.convulsions, R.string.diazepam3, R.string.convulsions1,
                            R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                            R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }else{
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin3, R.string.gentamicin3,
                            R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }

            }

        }else {
            if (ag >= 0.2 && ag < 0.4){
                if (s.contains("Convulsions")){
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin2, R.string.gentamicin2,
                            R.string.convulsions, R.string.diazepam2, R.string.convulsions1,
                            R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                            R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }else{
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin2, R.string.gentamicin2,
                            R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }
            }else if (ag >= 0.4 && ag < 1.0){
                if (s.contains("Convulsions")){
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin4, R.string.gentamicin4,
                            R.string.convulsions, R.string.diazepam4, R.string.convulsions1,
                            R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                            R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }else{
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin4, R.string.gentamicin4,
                            R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }
            }else if (ag >= 1.0 && ag < 3.0){
                if (s.contains("Convulsions")){
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin12, R.string.gentamicin12,
                            R.string.convulsions, R.string.diazepam12, R.string.convulsions1,
                            R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                            R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }else{
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin12, R.string.gentamicin12,
                            R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }

            }else if (ag >= 3.0){
                if (s.contains("Convulsions")){
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin3, R.string.gentamicin3,
                            R.string.convulsions, R.string.diazepam3, R.string.convulsions1,
                            R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                            R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }else{
                    messages = Arrays.asList(R.string.first_dose, R.string.ampicilin3, R.string.gentamicin3,
                            R.string.other1, R.string.other2, R.string.other3,
                            R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                            R.string.other8, R.string.refer_urgently);
                }

            }
        }

        return messages;

    }

    public List GetPneumoniaInstructions(float ag, String weight) {

        List<Integer> messages = new ArrayList<>();

        if (!weight.isEmpty()) {
            float we = Float.parseFloat(weight);
            if (we >= 4.0 && we < 10.0){
                messages = Arrays.asList(R.string.amoxicillin2, R.string.pneumonia1, R.string.pneumonia2);
            }else if (we >= 10.0 && we < 14.0){
                messages = Arrays.asList(R.string.amoxicillin12, R.string.pneumonia1, R.string.pneumonia2);
            }else if (we >= 14.0){
                messages = Arrays.asList(R.string.amoxicillin3, R.string.pneumonia1, R.string.pneumonia2);
            }
        }else {
            if (ag >= 0.2 && ag < 1.0){
                messages = Arrays.asList(R.string.amoxicillin2, R.string.pneumonia1, R.string.pneumonia2);
            }else if (ag >= 1.0 && ag < 3.0){
                messages = Arrays.asList(R.string.amoxicillin12, R.string.pneumonia1, R.string.pneumonia2);
            }else if (ag >= 3.0 && ag < 5.0){
                messages = Arrays.asList(R.string.amoxicillin3, R.string.pneumonia1, R.string.pneumonia2);
            }
        }

        return messages;

    }

    public void GetPointsFromRR(float rate, float age){

    }

}
