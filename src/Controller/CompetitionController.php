<?php

namespace App\Controller;

use App\Entity\Competition;
use App\Form\CompetitionType;
use App\Repository\CompetitionRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/competition")
 */
class CompetitionController extends AbstractController
{
    /**
     * @Route("/", name="competition_index", methods={"GET"})
     */
    public function index(CompetitionRepository $competitionRepository): Response
    {
        return $this->render('competition/index.html.twig', [
            'competitions' => $competitionRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="competition_new", methods={"GET","POST"})
     */
    public function new(Request $request, FlashyNotifier $flashy): Response
    {
        $competition = new Competition();
        $form = $this->createForm(CompetitionType::class, $competition);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($competition);
            $entityManager->flush();
            $flashy->success('Competition Ajouter!');
            return $this->redirectToRoute('competition_index');
        }

        return $this->render('competition/new.html.twig', [
            'competition' => $competition,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idCompetition}", name="competition_show", methods={"GET"})
     */
    public function show(Competition $competition): Response
    {
        return $this->render('competition/show.html.twig', [
            'competition' => $competition,
        ]);
    }

    /**
     * @Route("/{idCompetition}/edit", name="competition_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Competition $competition, FlashyNotifier $flashy): Response
    {
        $form = $this->createForm(CompetitionType::class, $competition);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();
            $flashy->info('Competition modifier!');
            return $this->redirectToRoute('competition_index');
        }

        return $this->render('competition/edit.html.twig', [
            'competition' => $competition,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idCompetition}", name="competition_delete", methods={"POST"})
     */
    public function delete(Request $request, Competition $competition, FlashyNotifier $flashy): Response
    {
        if ($this->isCsrfTokenValid('delete'.$competition->getIdCompetition(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($competition);
            $entityManager->flush();
            $flashy->error('Competition Supprimer!');
        }

        return $this->redirectToRoute('competition_index');
    }
}
