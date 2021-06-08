<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\AdminService;

class AdminController extends Controller
{
    public function __construct(AdminService $adminService) {
        $this->adminService = $adminService;
    }
    
    public function showAdmin(Request $request)
    {
        return $this->adminService->showAdmin();
    }

    public function showUserAdmin(Request $request, $username, $password)
    {
        $data = $request->all();
        return $this->adminService->showUserAdmin($username,$password);
    }



}
