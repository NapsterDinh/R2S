<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\ModuleService;

class ModuleController extends Controller
{
    public function __construct(ModuleService $moduleService) {
        $this->moduleService = $moduleService;
    }

    public function showModule(Request $request, $isAll)
    {
        return $this->moduleService->showModule($isAll);
    }

    public function getAll(Request $request) {
        return $this->moduleService->getAll();
    }

    public function insertModule(Request $request) {
        $data = $request->all();
        return $this->moduleService->insertModule($data);
    }

    public function editModule(Request $request) {
        $data = $request->all();
        return $this->moduleService->editModule($data);
    }

    public function deleteModule(Request $request) {
        $data = $request->all();
        return $this->moduleService->deleteModule($data);
    }
}
