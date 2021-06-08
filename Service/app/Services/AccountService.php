<?php
namespace App\Services;

use App\Models\Trainee;
use Illuminate\Support\Facades\DB;

class AccountService {
    public function __construct() {

    }

    public function showTrainee() {
        $user = DB::table('Trainee')->select(
            'UserName',
            'Email',
            'Phone',
            'Address',
            'Name',
            'IsActive',
            'Password',
            'ActivationCode',
            'ResetPasswordCode'
        )->get();

        return json_encode($user);
    }

    public function insertTrainee($data) {
        $user = Trainee::create([
            'UserName' => $data['UserName'],
            'Email' => $data['Email'],
            'Phone' => $data['Phone'],
            'Address' => $data['Address'],
            'Name' => $data['Name'],
            'IsActive' => $data['IsActive'],
            'Password' => $data['Password'],
            'ActivationCode' => $data['ActivationCode'],
            'ResetPasswordCode' => $data['ResetPasswordCode'],
        ]);

        return json_encode($user);
    }

    public function showUserTrainee($username,$password) {
        $user = DB::table('Trainee')
        ->where('UserName','=',$username)
        ->where('Password','=',$password)
        ->get();

        return json_encode($user);
    }
}