<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\EnrollmentService;

class EnrollmentController extends Controller
{
    public function __construct(EnrollmentService $enrollmentService) {
        $this->enrollmentService = $enrollmentService;
    }


    public function getAll(Request $request) {
        return $this->enrollmentService->getAll();
    }


}
