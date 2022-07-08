package com.ug.air.alrite.Utils.Calculations;

import com.ug.air.alrite.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Instructions {

    public List GetInstructions(int ag, String weight, String s){

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

            }else {
                if (ag >= 2 && ag < 4){
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
                }else if (ag >= 4 && ag < 12){
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
                }else if (ag >= 12 && ag < 36){
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

                }else if (ag >= 36){
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

        }else {
            if (ag >= 2 && ag < 4){
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
            }else if (ag >= 4 && ag < 12){
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
            }else if (ag >= 12 && ag < 36){
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

            }else if (ag >= 36){
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

    public List GetPneumoniaInstructions(int ag, String weight) {

        List<Integer> messages = new ArrayList<>();

        if (!weight.isEmpty()) {
            float we = Float.parseFloat(weight);
            if (we >= 4.0 && we < 10.0){
                messages = Arrays.asList(R.string.amoxicillin2, R.string.pneumonia1, R.string.pneumonia2);
            }else if (we >= 10.0 && we < 14.0){
                messages = Arrays.asList(R.string.amoxicillin12, R.string.pneumonia1, R.string.pneumonia2);
            }else if (we >= 14.0){
                messages = Arrays.asList(R.string.amoxicillin3, R.string.pneumonia1, R.string.pneumonia2);
            }else {
                if (ag >= 2 && ag < 12){
                    messages = Arrays.asList(R.string.amoxicillin2, R.string.pneumonia1, R.string.pneumonia2);
                }else if (ag >= 12 && ag < 36){
                    messages = Arrays.asList(R.string.amoxicillin12, R.string.pneumonia1, R.string.pneumonia2);
                }else if (ag >= 36 && ag < 60){
                    messages = Arrays.asList(R.string.amoxicillin3, R.string.pneumonia1, R.string.pneumonia2);
                }
            }
        }else {
            if (ag >= 2 && ag < 12){
                messages = Arrays.asList(R.string.amoxicillin2, R.string.pneumonia1, R.string.pneumonia2);
            }else if (ag >= 12 && ag < 36){
                messages = Arrays.asList(R.string.amoxicillin12, R.string.pneumonia1, R.string.pneumonia2);
            }else if (ag >= 36 && ag < 60){
                messages = Arrays.asList(R.string.amoxicillin3, R.string.pneumonia1, R.string.pneumonia2);
            }
        }

        return messages;

    }

    public List GetFebrilInstructions(int ag, String weight) {

        List<Integer> messages = new ArrayList<>();

        if (!weight.isEmpty()) {
            float we = Float.parseFloat(weight);
            if (we >= 4.0 && we < 6.0){
                messages = Arrays.asList(R.string.febril1, R.string.artesunate2, R.string.iartesunate2, R.string.quinine2, R.string.paracetamol2, R.string.refer_urgently);
            }else if (we >= 6.0 && we < 14.0){
                if (we < 10){
                    messages = Arrays.asList(R.string.febril1, R.string.artesunate4, R.string.iartesunate4, R.string.quinine4, R.string.paracetamol2, R.string.refer_urgently);
                }else if (we >= 10 && we < 12){
                    messages = Arrays.asList(R.string.febril1, R.string.artesunate4, R.string.iartesunate12, R.string.quinine12, R.string.paracetamol2, R.string.refer_urgently);
                }else if (we >= 12 && we < 14){
                    messages = Arrays.asList(R.string.febril1, R.string.artesunate5, R.string.iartesunate12, R.string.quinine24, R.string.paracetamol2, R.string.refer_urgently);
                }
            }else if (we >= 14.0){
                messages = Arrays.asList(R.string.febril1, R.string.artesunate4, R.string.iartesunate3, R.string.quinine3, R.string.paracetamol3, R.string.refer_urgently);
            }else {
                if (ag >= 2 && ag < 4 ){
                    messages = Arrays.asList(R.string.febril1, R.string.artesunate2, R.string.iartesunate2, R.string.quinine2, R.string.paracetamol2, R.string.refer_urgently);
                }else if (ag >= 4 && ag < 12){
                    messages = Arrays.asList(R.string.febril1, R.string.artesunate4, R.string.iartesunate4, R.string.quinine4, R.string.paracetamol2, R.string.refer_urgently);
                }else if (ag >= 12 && ag < 36 ){
                    if (ag < 24 ){
                        messages = Arrays.asList(R.string.febril1, R.string.artesunate4, R.string.iartesunate12, R.string.quinine12, R.string.paracetamol2, R.string.refer_urgently);
                    }else {
                        messages = Arrays.asList(R.string.febril1, R.string.artesunate5, R.string.iartesunate12, R.string.quinine24, R.string.paracetamol2, R.string.refer_urgently);
                    }
                }else if (ag >= 36  ){
                    messages = Arrays.asList(R.string.febril1, R.string.artesunate5, R.string.iartesunate3, R.string.quinine3, R.string.paracetamol3, R.string.refer_urgently);
                }
            }
        }else {
            if (ag >= 2 && ag < 4 ){
                messages = Arrays.asList(R.string.febril1, R.string.artesunate2, R.string.iartesunate2, R.string.quinine2, R.string.paracetamol2, R.string.refer_urgently);
            }else if (ag >= 4 && ag < 12){
                messages = Arrays.asList(R.string.febril1, R.string.artesunate4, R.string.iartesunate4, R.string.quinine4, R.string.paracetamol2, R.string.refer_urgently);
            }else if (ag >= 12 && ag < 36 ){
                if (ag < 24 ){
                    messages = Arrays.asList(R.string.febril1, R.string.artesunate4, R.string.iartesunate12, R.string.quinine12, R.string.paracetamol2, R.string.refer_urgently);
                }else {
                    messages = Arrays.asList(R.string.febril1, R.string.artesunate5, R.string.iartesunate12, R.string.quinine24, R.string.paracetamol2, R.string.refer_urgently);
                }
            }else if (ag >= 36  ){
                messages = Arrays.asList(R.string.febril1, R.string.artesunate5, R.string.iartesunate3, R.string.quinine3, R.string.paracetamol3, R.string.refer_urgently);
            }
        }

        return messages;

    }

    public List GetHIVInfected(String care, String chest, int ag, String weight){

        List<Integer> messages = new ArrayList<>();

        if (ag >= 2 && ag < 12){
            if (ag < 6){
                if (care.equals("Yes")){
                    if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                        messages = Arrays.asList(R.string.hiv2, R.string.amoxicillin2v, R.string.hiv3, R.string.cotrimoxazole6);
                    }else{
                        messages = Arrays.asList(R.string.hiv2, R.string.hiv3, R.string.cotrimoxazole6);
                    }

                }else  if (care.equals("No")){
                    if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                        messages = Arrays.asList(R.string.hiv1, R.string.amoxicillin2v, R.string.hiv3, R.string.cotrimoxazole6);
                    }else{
                        messages = Arrays.asList(R.string.hiv1, R.string.hiv3, R.string.cotrimoxazole6);
                    }
                }
            }else {
                if (care.equals("Yes")){
                    if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                        messages = Arrays.asList(R.string.hiv2, R.string.amoxicillin2v, R.string.hiv3, R.string.cotrimoxazole5);
                    }else{
                        messages = Arrays.asList(R.string.hiv2, R.string.hiv3, R.string.cotrimoxazole5);
                    }

                }else  if (care.equals("No")){
                    if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                        messages = Arrays.asList(R.string.hiv1, R.string.amoxicillin2v, R.string.hiv3, R.string.cotrimoxazole5);
                    }else{
                        messages = Arrays.asList(R.string.hiv1, R.string.hiv3, R.string.cotrimoxazole5);
                    }
                }
            }
        }else if (ag >= 12 && ag < 36){
            if (care.equals("Yes")){
                if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                    messages = Arrays.asList(R.string.hiv2, R.string.amoxicillin12v, R.string.hiv3, R.string.cotrimoxazole5);
                }else{
                    messages = Arrays.asList(R.string.hiv2, R.string.hiv3, R.string.cotrimoxazole5);
                }

            }else  if (care.equals("No")){
                if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                    messages = Arrays.asList(R.string.hiv1, R.string.amoxicillin12v, R.string.hiv3, R.string.cotrimoxazole5);
                }else{
                    messages = Arrays.asList(R.string.hiv1, R.string.hiv3, R.string.cotrimoxazole5);
                }
            }
        }else if (ag >= 36 && ag < 60){
            if (care.equals("Yes")){
                if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                    messages = Arrays.asList(R.string.hiv2, R.string.amoxicillin3v, R.string.hiv3, R.string.cotrimoxazole5);
                }else{
                    messages = Arrays.asList(R.string.hiv2, R.string.hiv3, R.string.cotrimoxazole5);
                }

            }else  if (care.equals("No")){
                if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                    messages = Arrays.asList(R.string.hiv1, R.string.amoxicillin3v, R.string.hiv3, R.string.cotrimoxazole5);
                }else{
                    messages = Arrays.asList(R.string.hiv1, R.string.hiv3, R.string.cotrimoxazole5);
                }
            }
        }
        
        return messages;
    }

    public List GetHIVExposed(String care, String chest, int ag, String weight){

        List<Integer> messages = new ArrayList<>();

        if (ag >= 2 && ag < 12){
            if (ag < 06){
                if (care.equals("Yes")){
                    if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                        messages = Arrays.asList(R.string.hiv2, R.string.amoxicillin2v);
                    }else{
                        messages = Arrays.asList(R.string.hiv2, R.string.hiv3);
                    }

                }else  if (care.equals("No")){
                    if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                        messages = Arrays.asList(R.string.hiv1, R.string.amoxicillin2v);
                    }else{
                        messages = Arrays.asList(R.string.hiv1, R.string.hiv3);
                    }
                }
            }else {
                if (care.equals("Yes")){
                    if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                        messages = Arrays.asList(R.string.hiv2, R.string.amoxicillin2v);
                    }else{
                        messages = Arrays.asList(R.string.hiv2);
                    }

                }else  if (care.equals("No")){
                    if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                        messages = Arrays.asList(R.string.hiv1, R.string.amoxicillin2v);
                    }else{
                        messages = Arrays.asList(R.string.hiv1);
                    }
                }
            }
        }else if (ag >= 12 && ag < 36){
            if (care.equals("Yes")){
                if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                    messages = Arrays.asList(R.string.hiv2, R.string.amoxicillin12v, R.string.hiv3, R.string.cotrimoxazole5);
                }else{
                    messages = Arrays.asList(R.string.hiv2, R.string.hiv3, R.string.cotrimoxazole5);
                }

            }else  if (care.equals("No")){
                if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                    messages = Arrays.asList(R.string.hiv1, R.string.amoxicillin12v);
                }else{
                    messages = Arrays.asList(R.string.hiv1);
                }
            }
        }else if (ag >= 36 && ag < 60){
            if (care.equals("Yes")){
                if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                    messages = Arrays.asList(R.string.hiv2, R.string.amoxicillin3v, R.string.hiv3, R.string.cotrimoxazole5);
                }else{
                    messages = Arrays.asList(R.string.hiv2, R.string.hiv3, R.string.cotrimoxazole5);
                }

            }else  if (care.equals("No")){
                if (chest.equals("Mild") || chest.equals("Moderate/Severe")){
                    messages = Arrays.asList(R.string.hiv1, R.string.amoxicillin3v);
                }else{
                    messages = Arrays.asList(R.string.hiv1);
                }
            }
        }


        return messages;
    }

    public int GetPointsFromRR(double rate, int age){
        int point = 0;

        if ((rate < 60 && age < 2) || (rate < 50 && (age >= 2 && age < 12)) || (rate < 40 && (age >= 12 && age < 60))){
            point = 0;
        }else if (((rate >= 60 && rate < 70) && age < 2) || ((rate >= 50 && rate < 60) && (age >= 2 && age < 12)) || ((rate >= 40 && rate < 50) && (age >= 12 && age < 60))){
            point = 1;
        }else if (((rate >= 70 && rate < 80) && age < 2) || ((rate >= 60 && rate < 70) && (age >= 2 && age < 12)) || ((rate >= 50 && rate < 60) && (age >= 12 && age < 60))){
            point = 2;
        }else if ((rate >= 80 && age < 2) || (rate >= 70 && (age >= 2 && age < 12)) || (rate >= 60 && (age >= 12 && age < 60))){
            point = 3;
        }

        return point;
    }

    public int GetWheezing(String wheez, int pt){
        int point = 0;

        if (wheez.equals("Normal breath sounds")){
            point = 0;
        }else if (wheez.equals("Other abnormal breath sounds")){
            point = 2;
        }else if (wheez.equals("Wheezing")){
            point = 3;
        }

        point = point + pt;

        return point;
    }

    public int GetChestIndrawing(String chest, String grunt, int pt){
        int point = 0;

        if (chest.equals("No")){
            point = 0;
        }else if (chest.equals("Mild") && grunt.equals("No")){
            point = 1;
        }else if (chest.equals("Moderate/Severe") && grunt.equals("No")){
            point = 2;
        }else if ((chest.equals("Moderate/Severe") || chest.equals("Mild")) && grunt.equals("Yes")){
            point = 3;
        }

        point = point + pt;

        return point;

    }

}
