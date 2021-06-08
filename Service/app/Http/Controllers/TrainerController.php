<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\TrainerService;

class TrainerController extends Controller
{
    public function __construct(TrainerService $trainerService) {
        $this->trainerService = $trainerService;
    }

    public function showAllTrainer(Request $request)
    {
        return $this->trainerService->showAllTrainer();
    }

    public function showUserTrainer(Request $request, $username, $password)
    {
        $data = $request->all();
        return $this->trainerService->showUserTrainer($username,$password);
    }
    
}
