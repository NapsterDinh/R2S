<?php

namespace App\Http\Controllers\Account;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\AccountService;

class AccountController extends Controller
{
    public function __construct(AccountService $accountService) {
        $this->accountService = $accountService;
    }

    public function showTrainee(Request $request) {
        return $this->accountService->showTrainee();
    }

    public function insertTrainee(Request $request) {
        $data = $request->all();
        return $this->accountService->insertTrainee($data);
    }

    public function showUserTrainee(Request $request, $username, $password)
    {
        $data = $request->all();
        return $this->accountService->showUserTrainee($username,$password);
    }
}
