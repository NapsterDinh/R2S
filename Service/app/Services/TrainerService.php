<?php
namespace App\Services;

use App\Models\AssignmentRequest;
use Illuminate\Support\Facades\DB;

class TrainerService {
    public function __construct() {

    }

    public function showAllTrainer() {
        $trainer = DB::table('Trainer')->select(
            'UserName as userName',
            'Email as email',
            'Phone as phone',
            'Address as address',
            'Name as name',
            'IsActive as isActive',
            'Password as password',
            'IdSkill as idSkill',
            'ActivationCode as activationCode',
            'ResetPasswordCode as resetPasswordCode',
            'IsReceiveNotification as isReceiveNotification',
        )
        ->get();

        return json_encode($trainer);
    }

    public function showUserTrainer($username,$password) {
        $user = DB::table('Trainer')
        ->where('userName','=',$username)
        ->where('password','=',$password)
        ->get();

        return json_encode($user);
    }
}
