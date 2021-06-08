<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\AssignmentService;

class AssignmentController extends Controller
{
    public function __construct(AssignmentService $assignmentService) {
        $this->assignmentService = $assignmentService;
    }

    public function showAllAssignment(Request $request, $idUser, $role)
    {
        return $this->assignmentService->showAllAssignment($idUser,$role);
    }

    public function searchAssignment(Request $request, $idUser, $role, $str_search)
    {
        return $this->assignmentService->searchAssignment($idUser,$role, $str_search);
    }
    
    public function addAssignment(Request $request)
    {
        $new_Assignment = $request->all();
        return $this->assignmentService->addAssignment($new_Assignment);
    }

    public function updateAssignment(Request $request)
    {
        $update_assignment = $request->all();
        return $this->assignmentService->updateAssignment($update_assignment);
    }

    public function deleteAssignment(Request $request, $isFirstStep)
    {
        $deleted_Assignment = $request->all();
        return $this->assignmentService->deleteAssignment($deleted_Assignment,$isFirstStep);
    }

}
