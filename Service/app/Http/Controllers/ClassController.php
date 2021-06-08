<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\ClassService;

class ClassController extends Controller
{
    public function __construct(ClassService $classService) {
        $this->classService = $classService;
    }

    public function showClass(Request $request,$isAll)
    {
        return $this->classService->showClass($isAll);
    }

    public function getAll(Request $request) {
        return $this->classService->getAll();
    }

    public function insertClass(Request $request) {
        $data = $request->all();
        return $this->classService->insertClass($data);
    }

    public function editClass(Request $request) {
        $data = $request->all();
        return $this->classService->editClass($data);
    }

    public function deleteClass(Request $request) {
        $data = $request->all();
        return $this->classService->deleteClass($data);
    }

}
