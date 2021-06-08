<?php
namespace App\Services;

use App\Models\Admin;
use Illuminate\Support\Facades\DB;

class AdminService {
    public function __construct() {

    }
        
    public function showAdmin() {
        $user = DB::table('Admin')->select(
            'UserName',
            'Name',
            'Email',
            'Password'
        )->get();

        return json_encode($user);
    }
    

    public function showUserAdmin($username,$password) {
        $user = DB::table('Admin')
        ->where('UserName','=',$username)
        ->where('Password','=',$password)
        ->get();

        return json_encode($user);
        
    }
}