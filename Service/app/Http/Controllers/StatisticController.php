<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\StatisticService;

class StatisticController extends Controller
{
    public function __construct(StatisticService $statisticService) {
        $this->statisticService = $statisticService;
    }

    public function showOverview(Request $request, $isAdmin, $idUSer, $idClass, $idModule)
    {
        return $this->statisticService->showOverview($isAdmin, $idUSer, $idClass, $idModule);
    }



}
