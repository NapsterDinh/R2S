<?php
namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\FeedbackService;

class FeedbackController extends Controller
{
    public function __construct(FeedbackService $feedbackService) {
        $this->feedbackService = $feedbackService;
    }

    public function showFeedback(Request $request)
    {
        return $this->feedbackService->showFeedback();
    }


}